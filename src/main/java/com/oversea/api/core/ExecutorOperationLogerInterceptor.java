package com.oversea.api.core;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oversea.common.request.RequestBaseParams;

public class ExecutorOperationLogerInterceptor implements MethodInterceptor{
		
    private static Log log = LogFactory.getLog("CONSUMING_TIME");  
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] args=invocation.getArguments();
		String operatorType = (String)args[0];
		RequestBaseParams rbp=(RequestBaseParams)args[1];
		long s_time = System.currentTimeMillis();
		Object obj=invocation.proceed();
		long e_time = System.currentTimeMillis();
		long consumer = e_time-s_time;
		if(consumer > 2000){
			if(consumer > 5000){
				log.error(String.format("[%s]接口耗时:%d", operatorType==null?"WARNING_"+rbp.getMethod():"WARNING_"+operatorType ,consumer));
			}else{
				log.error(String.format("[%s]接口耗时:%d", operatorType==null?rbp.getMethod():operatorType ,consumer));
			}
		}
		return obj;
	}
}
