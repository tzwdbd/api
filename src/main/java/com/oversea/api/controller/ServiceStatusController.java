package com.oversea.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.oversea.common.manager.TestManager;
import com.oversea.common.provide.RemoteSeriveConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.InetAddress;

@Controller
public class ServiceStatusController {
	
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print("haha");
		response.getWriter().close();
		
		return null;
	}
	
	
	
	@RequestMapping("/dubbo22")
	public String dubbo22(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ApplicationConfig application = new ApplicationConfig();
		application.setName("CLIENT_API_" + InetAddress.getLocalHost().getHostAddress().replace(".", ""));
		ReferenceConfig<TestManager> reference = new ReferenceConfig<TestManager>();
	    reference.setApplication(application);
		reference.setInterface(TestManager.class);
        reference.setVersion(RemoteSeriveConfig.VERSION);
		reference.setUrl("dubbo://122.225.114.22:20880/com.oversea.common.manager.TestManager"); 
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		TestManager testManager = cache.get(reference);
		String text = testManager.testHelloDubbo();
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(text);
		response.getWriter().close();
		
		return null;
	}
	@RequestMapping("/dubbo19")
	public String dubbo19(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ApplicationConfig application = new ApplicationConfig();
		application.setName("CLIENT_API_" + InetAddress.getLocalHost().getHostAddress().replace(".", ""));
		ReferenceConfig<TestManager> reference = new ReferenceConfig<TestManager>();
	    reference.setApplication(application);
		reference.setInterface(TestManager.class);
        reference.setVersion(RemoteSeriveConfig.VERSION);
		reference.setUrl("dubbo://122.225.114.19:20880/com.oversea.common.manager.TestManager"); 
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		TestManager testManager = cache.get(reference);
		String text = testManager.testHelloDubbo();
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(text);
		response.getWriter().close();
		
		return null;
	}
	
	
	@RequestMapping("/dubboCache22")
	public String dubboCache20(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ApplicationConfig application = new ApplicationConfig();
		application.setName("CLIENT_API_" + InetAddress.getLocalHost().getHostAddress().replace(".", ""));
		ReferenceConfig<TestManager> reference = new ReferenceConfig<TestManager>();
	    reference.setApplication(application);
		reference.setInterface(TestManager.class);
        reference.setVersion(RemoteSeriveConfig.VERSION);
		reference.setUrl("dubbo://122.225.114.22:20880/com.oversea.common.manager.TestManager"); 
		ReferenceConfigCache cache = ReferenceConfigCache.getCache();
		TestManager testManager = cache.get(reference);
		String content = testManager.getAllJvmCacheSize();
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print("缓存数量内容:"+content);
		response.getWriter().close();
		
		return null;
	}
	
}
