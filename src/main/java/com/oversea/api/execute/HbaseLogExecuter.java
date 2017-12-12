package com.oversea.api.execute;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;
import com.oversea.common.util.DateUtil;
import com.oversea.common.util.StringUtil;

public class HbaseLogExecuter implements LogExecuter {
	
	private static Logger logger = LoggerFactory.getLogger(HbaseLogExecuter.class);
	
	// @Resource
    // private ResourcesManager resourcesManager;
	
	private String tableName;
	private String zkQuorum;
	private String zkPort;
	private String zkRetry;
	private String hbaseRetryNum;
	private String hbasePause;
	
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
	
	private ExecutorService executor;
	private Configuration config;
	private Connection conn;
	private TableName TABLE;
	private Table table;
	
	@Override
	public void init() {
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", zkQuorum);
		config.set("hbase.zookeeper.property.clientPort", zkPort);
		config.set("hbase.client.retries.number", hbaseRetryNum);
		config.set("hbase.client.pause", hbasePause);
		config.set("zookeeper.recovery.retry", zkRetry);
		
		executor = Executors.newFixedThreadPool(5);
		
		TABLE = TableName.valueOf(tableName);
		
		try {
			conn = ConnectionFactory.createConnection(config, executor);
			table = conn.getTable(TABLE);
		} catch (Exception e) {
			logger.error("HbaseLogExecuter_createConnection_error: ", e);
		}
		
		logger.error("HbaseLogExecuter init");
	}
	
	@Override
	public void close() {
		try {
			table.close();
			conn.close();
		} catch (Exception e) {
			logger.error("HbaseLogExecuter_close_error: ", e);
		}
	
		logger.error("HbaseLogExecuter close");
	}
	
	@Override
	public void log(String from, RequestBaseParams requestParams, ResponseBaseParams responseParams) {
		/*Map<String, Resources> resMap = resourcesManager.getSaleResourceByMap(ResourcesType.HBASE_API_USER_LOG_TYPE.getName());
		Resources switchRes = resMap.get(ResourcesType.HBASE_API_USER_LOG_SWITCH.getName());
		
		if(switchRes != null && LOG_SWITCH_OFF.equalsIgnoreCase(switchRes.getResValue())) {
			logger.error("HbaseLogExecuter_log: log off");
			return;
		}*/
		
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
		
		/*Resources modeRes = resMap.get(ResourcesType.HBASE_API_USER_LOG_MODE.getName());
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
		}*/
		
		try {
			if(conn == null || conn.isAborted() || conn.isClosed()) {
				logger.error("HbaseLogExecuter_log: conn is null or closed, from={}, method={}, userId={}", from, method, userId);
				return;
			}
			
			HBaseAdmin hBaseAdmin = (HBaseAdmin) conn.getAdmin();
			
			if(hBaseAdmin == null) {
				logger.error("HbaseLogExecuter_log: hBaseAdmin is null, from={}, method={}, userId={}", from, method, userId);
				return;
			}
			
			if(!hBaseAdmin.tableExists(TABLE)) {
				HTableDescriptor tableDescriptor = new HTableDescriptor(TABLE);
				HColumnDescriptor family1 = new HColumnDescriptor(PLATFORM);
				HColumnDescriptor family2 = new HColumnDescriptor(REQ);
				HColumnDescriptor family3 = new HColumnDescriptor(RESP);
				HColumnDescriptor family4 = new HColumnDescriptor(STATUS);
				tableDescriptor.addFamily(family1);
				tableDescriptor.addFamily(family2);
				tableDescriptor.addFamily(family3);
				tableDescriptor.addFamily(family4);
				hBaseAdmin.createTable(tableDescriptor);
			}
			
	        String rowKey = getRowKey(userId, method);
			Put put = new Put(rowKey.getBytes());
			
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

	public String getZkQuorum() {
		return zkQuorum;
	}

	public void setZkQuorum(String zkQuorum) {
		this.zkQuorum = zkQuorum;
	}

	public String getZkPort() {
		return zkPort;
	}

	public void setZkPort(String zkPort) {
		this.zkPort = zkPort;
	}

	public String getZkRetry() {
		return zkRetry;
	}

	public void setZkRetry(String zkRetry) {
		this.zkRetry = zkRetry;
	}

	public String getHbaseRetryNum() {
		return hbaseRetryNum;
	}

	public void setHbaseRetryNum(String hbaseRetryNum) {
		this.hbaseRetryNum = hbaseRetryNum;
	}

	public String getHbasePause() {
		return hbasePause;
	}

	public void setHbasePause(String hbasePause) {
		this.hbasePause = hbasePause;
	}
}
