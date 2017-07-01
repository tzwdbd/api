package com.oversea.api.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 纪录方法耗时
 * 
 * @author yaohb
 * 
 */
public class TimeConsumingLogUtil {

	private static Log log = LogFactory.getLog("CONSUMING_TIME");

	public static void log(String interfaceName , String methodName , long consuming){
		// 耗时>500毫秒才纪录
		if(consuming > 500){
			log.error(interfaceName+":"+methodName+":"+consuming);
		}
	}
}
