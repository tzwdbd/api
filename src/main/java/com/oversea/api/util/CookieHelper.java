package com.oversea.api.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.oversea.common.encode.ThreeDES;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.exception.ProcessStatusCode;


public class CookieHelper {
	private static Log log = LogFactory.getLog(CookieHelper.class);
	
	/**
	 * 这个是特殊用途，取数据库userId经过ThreeDES编码后的值
	 * 下发给h5浏览器，h5端作为api服务器请求的cookie参数
	 */
	public static final String userCookieName   = "os_cookie_id";
    public static final String cookie_domain = "haihu.com";
    public static final Integer maxAge       = -1;
	
    public static void addCookie(HttpServletResponse response, String openId) {
    	try {
    		openId = URLEncoder.encode(openId, "UTF-8");
			Cookie cookie = new Cookie(userCookieName,openId);
			cookie.setDomain(cookie_domain);
			cookie.setMaxAge(maxAge);
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			log.error(String.format("CookieHelper addCookie openId=[%s] error=[%s]",openId,e.getMessage()),e);
		}
	}
    
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue) {
    	try {
			cookieValue = URLEncoder.encode(cookieValue, "UTF-8");
			Cookie cookie = new Cookie(cookieName, cookieValue);
			cookie.setDomain(cookie_domain);
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			log.error("CookieHelper addCookie error"+e.getMessage(),e);
		}
	}
    
   
    public static String getOpenIdFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		log.error("cookies="+(cookies==null?"cookies is null":cookies.toString()));
		
		if (null != cookies) {
			for (Cookie c : cookies) {
				
				log.error(String.format("cookies name=[%s] value=[%s]",c.getName(), c.getValue()));
				
//				if (userCookieName.equalsIgnoreCase(c.getName()) && cookie_domain.equalsIgnoreCase(c.getDomain())) {
				if (userCookieName.equalsIgnoreCase(c.getName())) {
					try {
						return URLDecoder.decode(c.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						log.error("CookieHelper getOpenIdFromCookie error1"+e.getMessage(),e);
					}
				}
			}
		}
		return null;
	}
    
    public static long getUserId(HttpServletRequest request){
    	String cookie = CookieHelper.getOpenIdFromCookie(request);
    	log.error("getUserId_cookie = "+cookie);
		long userId = -1L;
		if(!StringUtils.isEmpty(cookie)){
			userId = Long.valueOf(ThreeDES.decryptMode(cookie));	
			log.error("getUserId_userId = "+userId);
		}
		return userId;
    }
    
    public static String getOpenIdFromCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		
		log.error("cookies="+(cookies==null?"cookies is null":cookies.toString()));
		
		if (null != cookies) {
			for (Cookie c : cookies) {
				
				log.error(String.format("cookies name=[%s] value=[%s]",c.getName(), c.getValue()));
				
//				if (cookieName.equalsIgnoreCase(c.getName()) && cookie_domain.equalsIgnoreCase(c.getDomain())) {
				if (cookieName.equalsIgnoreCase(c.getName())) {
					try {
						return URLDecoder.decode(c.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						log.error("CookieHelper getOpenIdFromCookie error2"+e.getMessage(),e);
					}
				}
			}
		}
		return null;
	}
}
