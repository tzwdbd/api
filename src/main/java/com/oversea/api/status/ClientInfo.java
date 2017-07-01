package com.oversea.api.status;


/**
 * 记录客户端信息的线程变量集合类
 * @author yaohb
 *
 */
public class ClientInfo {
	/**
	 * 客户端类型
	 */
	public static ThreadLocal<String> PRODUCT_ID = new ThreadLocal<String>();
	/**
	 * 客户端版本
	 */
	public static ThreadLocal<Float> PRODUCT_VERSION = new ThreadLocal<Float>();
	/**
	 * 用户ID
	 */
	public static ThreadLocal<Long> USER_ID = new ThreadLocal<Long>();
	
	/**
	 * 客户端渠道
	 */
	public static ThreadLocal<String> PRODUCT_CHANNEL = new ThreadLocal<String>();
	/**
	 * 重置客户端cookie信息
	 */
	public static ThreadLocal<Boolean> COOKIE_SENDBAK = new ThreadLocal<Boolean>();
}
