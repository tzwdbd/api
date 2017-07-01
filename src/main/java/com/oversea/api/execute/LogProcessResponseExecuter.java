package com.oversea.api.execute;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oversea.api.dao.RequestStatisticsRecordDAO;
import com.oversea.api.domain.RequestStatisticsRecord;
import com.oversea.api.mongo.ApiMongoTemplete;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;

/**
 * fengjian
 * 15/7/1.
 */
public class LogProcessResponseExecuter {

    @Resource
    private RequestStatisticsRecordDAO requestStatisticsRecordDAO;
    @Resource
	private ApiMongoTemplete apiMongoTemplete;
    
    private  static Integer BATCH_INSERT_SIZE=15;
    
    private static AtomicLong recordActomicKey =new AtomicLong(0);
    
    private static long  INCRMENT_KEY=1L;
    
    private static Map<Long,RequestStatisticsRecord> logMap=new HashMap<Long,RequestStatisticsRecord>();

    private static final Log log = LogFactory.getLog(LogProcessResponseExecuter.class);

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(20, 20, Long.MAX_VALUE, TimeUnit.NANOSECONDS,new LinkedTransferQueue<Runnable>(), Executors.defaultThreadFactory());

    public void log(RequestBaseParams requestParams, ResponseBaseParams responseParams) throws OverseaException {
        long s_time = System.currentTimeMillis();
        final RequestStatisticsRecord requestStatisticsRecord = new RequestStatisticsRecord();
        requestStatisticsRecord.setMethod(requestParams.getMethod());
        requestStatisticsRecord.setStartTime(new Date(s_time));
        requestStatisticsRecord.setRequestBaseParams(requestParams);
        requestStatisticsRecord.setResponseBaseParams(responseParams);
        requestStatisticsRecord.setResultStatus(responseParams.getResponsePublicParams().getStatus());
        long e_time = System.currentTimeMillis();
        requestStatisticsRecord.setExecutionTime(e_time-s_time);
        logMap.put(recordActomicKey.addAndGet(INCRMENT_KEY), requestStatisticsRecord);
        if (logMap!=null && logMap.size()>=BATCH_INSERT_SIZE) {
        	List<RequestStatisticsRecord> logList =null;
			//同步处理map clear
			synchronized (logMap) {
				logList=new ArrayList<RequestStatisticsRecord>(logMap.values());
				logMap.clear();
			}
			log(logList);
        }
    }

    private void log(final List<RequestStatisticsRecord> requestStatisticsRecordList){
        try {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                    	apiMongoTemplete.insertAll(requestStatisticsRecordList);
                    } catch (Exception e) {
                        log.error("LogProcessResponseExecuter run ",e);
                    }
                }
            });
        } catch (Exception e) {
            log.error("ERROR IN LogProcessResponseExecuter  log method ",e);
        }
    }

}
