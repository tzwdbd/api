package com.oversea.api.util;

import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	
	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	public static String postData(String url, String key, String value) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
		post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000 * 60);
		post.setParameter(key, value);
		String result = null;
		try {
			client.executeMethod(post);
			result = new String(post.getResponseBody(), "UTF-8");
		} catch (Exception e) {
			log.error("HttpUtil_postData_error: url={}, key={}, value={}", url, key, value);
			log.error("HttpUtil_postData_error: ", e);
		} finally {
			post.releaseConnection();
		}
		return result;
	}
    
    public static String postData(String url, Map<String, String> datas) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
		post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 1000 * 60);
		if (null != datas && 0 != datas.size()) {
			NameValuePair[] nameValues = new NameValuePair[datas.size()];
			int i = 0;
			for (String key : datas.keySet()) {
				nameValues[i] = new NameValuePair();
				nameValues[i].setName(key);
				nameValues[i].setValue(datas.get(key));
				i++;
			}
			post.setRequestBody(nameValues);
		}
		String result = null;
		try {
			client.executeMethod(post);
			result = new String(post.getResponseBody(), "UTF-8");
		} catch (Exception e) {
			log.error("HttpUtil_postData_error: url={}, datas={}", url, datas);
			log.error("HttpUtil_postData_error: ", e);
		} finally {
			post.releaseConnection();
		}
		return result;
	}
}
