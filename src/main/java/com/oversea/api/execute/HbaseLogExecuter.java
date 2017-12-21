package com.oversea.api.execute;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.oversea.common.domain.Resources;
import com.oversea.common.enums.ResourcesType;
import com.oversea.common.manager.ResourcesManager;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;
import com.oversea.common.util.DateUtil;
import com.oversea.common.util.StringUtil;

public class HbaseLogExecuter {
	
	private static Logger logger = LoggerFactory.getLogger(HbaseLogExecuter.class);
	
	@Resource
	private HbaseUtil hbaseUtil;
	@Resource
    private ResourcesManager resourcesManager;
	
	private static ExecutorService logExecutor = new ThreadPoolExecutor(5, 10, 60L,
    		TimeUnit.SECONDS,
    		new ArrayBlockingQueue<Runnable>(20000),
    		Executors.defaultThreadFactory(),
    		new ThreadPoolExecutor.DiscardOldestPolicy());
	
	private static final String METHOD = "method";
	private static final String PLATFORM = "plat";
	private static final String REQ = "req";
	private static final String RESP = "resp";
	private static final String STATUS = "status";
	private static final String RESP_STATUS = "-999";
	private static final String USER_ID_NO_LOGIN = "XXXXXXXX";
	
	public static final String LOG_SWITCH_OFF = "off";
	public static final String LOG_MODE_BLACK = "black";
	
	public static final String FROM_MAIN = "service";
	public static final String FROM_H5 = "h5";
	
	private String tableName;
	
	public void log(final String from, final RequestBaseParams requestParams, final ResponseBaseParams responseParams) {
		try {
			logExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                    	put(from, requestParams, responseParams);
                    } catch (Exception e) {
                        logger.error("HbaseLogExecuter_log_submit_error", e);
                    }
                }
            });
        } catch (Exception e) {
        	logger.error("HbaseLogExecuter_log_error: ", e);
        }
	}
	
	private void put(String from, RequestBaseParams requestParams, ResponseBaseParams responseParams) {
		try {
			Map<String, Resources> resMap = resourcesManager.getSaleResourceByMap(ResourcesType.HBASE_API_USER_LOG_TYPE.getName());
			Resources switchRes = resMap.get(ResourcesType.HBASE_API_USER_LOG_SWITCH.getName());
			
			if(switchRes != null && LOG_SWITCH_OFF.equalsIgnoreCase(switchRes.getResValue())) {
				logger.error("HbaseLogExecuter_log: log off");
				return;
			}
			
			if(StringUtil.isBlank(from)) {
				logger.error("HbaseLogExecuter_log: from is null");
				return;
			}
			
			if(requestParams == null) {
				logger.error("HbaseLogExecuter_log: requestParams is null, from={}", from);
				return;
			}
			
			String method = requestParams.getMethod();
			Long userId = requestParams.getUser_id();
			
			if(StringUtil.isBlank(method)) {
				logger.error("HbaseLogExecuter_log: method is null, from={}", from);
				return;
			}
			
			logger.info("HbaseLogExecuter_log: from={}, method={}, userId={}", from, method, userId);
			
			Resources modeRes = resMap.get(ResourcesType.HBASE_API_USER_LOG_MODE.getName());
			String logMode = modeRes == null ? LOG_MODE_BLACK : modeRes.getResValue();
			
			if(LOG_MODE_BLACK.equals(logMode)) { // 黑名单
				List<Resources> blackList = resourcesManager.getSaleResourceByType(ResourcesType.HBASE_API_USER_LOG_MODE_BLACK_TYPE.getName());
				
				if(blackList != null && blackList.size() > 0) {
					for(Resources res : blackList) {
						if(method.equals(res.getResValue())) {
							return;
						}
					}
				}
			} else { // 白名单
				boolean logFlag = false;
				List<Resources> whiteList = resourcesManager.getSaleResourceByType(ResourcesType.HBASE_API_USER_LOG_MODE_WHITE_TYPE.getName());
				
				if(whiteList != null && whiteList.size() > 0) {
					for(Resources res : whiteList) {
						if(method.equals(res.getResValue())) {
							logFlag = true;
							break;
						}
					}
				}
				
				if(!logFlag) {
					return;
				}
			}
			
			Table table = hbaseUtil.getTable(tableName);
			
			if(table != null) {
				String rowKey = getRowKey(userId, method);
				Put put = new Put(rowKey.getBytes());
				
				if(StringUtil.isNotBlank(method)) {
					put.addColumn(Bytes.toBytes(METHOD), null, Bytes.toBytes(method));
				}
				
				String platform = requestParams.getProduct_id();
				
				if(StringUtil.isNotBlank(platform)) {
					put.addColumn(Bytes.toBytes(PLATFORM), null, Bytes.toBytes(platform));
				}
				
				put.addColumn(Bytes.toBytes(REQ), null, Bytes.toBytes(JSON.toJSONString(requestParams)));
				
				String respStatus = null;
				
				if(responseParams != null) {
					put.addColumn(Bytes.toBytes(RESP), null, Bytes.toBytes(JSON.toJSONString(responseParams)));
					
					if(responseParams.getResponsePublicParams() != null) {
						respStatus = responseParams.getResponsePublicParams().getStatus();
					}
				}
				
				put.addColumn(Bytes.toBytes(STATUS), null, Bytes.toBytes(StringUtil.isBlank(respStatus) ? RESP_STATUS : respStatus));
				
				table.put(put);
			}
		} catch(Exception e) {
			logger.error("HbaseLogExecuter_log_error: ", e);
		}
	}
	
	private String getRowKey(Long userId, String method) {
		StringBuffer rowKey = new StringBuffer();
		
		try {
			String userIdKey = null;
			
	        if(userId == null || userId == 0) {
	        	userIdKey = USER_ID_NO_LOGIN;
	        } else {
	        	StringBuffer userIdSb = new StringBuffer(String.valueOf(userId));
	        	userIdSb.reverse();
	        	
	        	int len = userIdSb.length();
	        	
	        	if(len < 8) {
	        		for(int i=0; i<8-len; i++) {
	        			userIdSb.append("X");
	        		}
	        	}
	        	
	        	userIdKey = userIdSb.toString();
	        }
	        
			String date = DateUtil.fromDate(new Date(), DateUtil.DATE_FORMAT_5);
			rowKey.append(userIdKey).append("_").append(date).append("_").append(method);
		} catch(Exception e) {
			logger.error("HbaseLogExecuter_log getRowKey error: ", e);
			rowKey = null;
		}
		
		return rowKey == null ? null : rowKey.toString();
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
