package com.oversea.api.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oversea.api.exception.RequestProcessInterruptException;
import com.oversea.common.core.ServiceMethod;
import com.oversea.common.exception.ProcessStatusCode;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;
import com.oversea.common.util.StringUtil;

/**
 * 线程执行类
 * @author fengjian
 *
 */
public class SystemOperationExecuter {
	
	private static Logger logger = LoggerFactory.getLogger(SystemOperationExecuter.class);
    
	private OverSeaServiceScanner overSeaServiceScanner;
    
    public OverSeaServiceScanner getOverSeaServiceScanner() {
        return overSeaServiceScanner;
    }

    public void setOverSeaServiceScanner(OverSeaServiceScanner overSeaServiceScanner) {
        this.overSeaServiceScanner = overSeaServiceScanner;
    }

    /**
     * 根据operationType调用相应的service中的方法执行
     * @param operationType
     * @param rbp
     * @return
     * @throws Exception
     * @throws RequestProcessInterruptException
     */
    public ResponseBaseParams execute(String operationType , RequestBaseParams rbp) throws RequestProcessInterruptException, Exception{
    	logger.error("SystemOperationExecuter: execute, operationType={}", operationType);
    	
        ServiceMethod bean = overSeaServiceScanner.getServiceByOperationType(operationType);
        if(bean == null){
            throw new RequestProcessInterruptException(ProcessStatusCode.PARAM_FORMAT_ERROR,"nvshenServiceScanner 未找到 ["+operationType+"] 相应的service内方法 .");
        }
        if(bean.isNeedLogin() && (rbp.getUser_id()==null || rbp.getUser_id()==0)){
        	throw new RequestProcessInterruptException(ProcessStatusCode.USER_UN_LOGIN,"本次操作 ["+operationType+"] 需要用户登陆后,才能继续 .");
        }else{
            //设置已经登录
            rbp.setLogin(true);
        }
        if(bean.isNeedCookie() && StringUtil.isEmpty(rbp.getCookie())){
        	throw new RequestProcessInterruptException(ProcessStatusCode.COOKIE_NOT_EXISTS,"本次操作 ["+operationType+"] 需要cookie信息 .");
        }
        
        try{
        	return (ResponseBaseParams)bean.getMethod().invoke(bean.getObject(), rbp);
        }catch(Exception e){
        	logger.error("SystemOperationExecuter: error, ", e);
        	throw e;
        }
    }
}
