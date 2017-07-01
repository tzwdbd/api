package com.oversea.api.listener;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class SystemConfigInitListener implements ServletContextListener{
	
	private static final Log log = LogFactory.getLog(SystemConfigInitListener.class);
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String prefix = sce.getServletContext().getRealPath("/");
        String file = sce.getServletContext().getInitParameter("systemConfigLocation");
        Properties props = new Properties();
        InputStream is = null;
        try {
            if (file.startsWith("classpath:")) {
                is = SystemConfigInitListener.class.getClassLoader().getResourceAsStream(file.substring(10));
            }else {
                String filePath = prefix + file;
                is = new FileInputStream(filePath);
            }
            props.load(is);
            for(Enumeration<Object> it = props.keys(); it.hasMoreElements();){
                String key = (String)it.nextElement();
                System.setProperty(key, props.getProperty(key));
                sce.getServletContext().setAttribute(key, props.getProperty(key));
            }
        }
        catch (Exception e) {
        	log.error("SystemConfigInitListener.ERROR " + e.getMessage());
        }finally{
            if(is != null){
                try{
                    is.close();
                }catch(Exception ignored){}
            }
        }
        
        
        file = sce.getServletContext().getInitParameter("systemConfigLocationOverride");
        Properties props2 = new Properties();
        InputStream is2 = null;
        try {
            if (file.startsWith("file:")) {
                is2 = new FileInputStream(file.substring(5).replaceAll("\\$\\{user.home\\}", System.getProperty("user.home").replaceAll("\\\\", "/")));
                log.error("==========user.home============ " + System.getProperty("user.home").replaceAll("\\\\", "/"));
            }else {
                String filePath = prefix + file;
                is2 = new FileInputStream(filePath);
            }
            props2.load(is2);
            for(Enumeration<Object> it = props2.keys(); it.hasMoreElements();){
                String key = (String)it.nextElement();
                System.setProperty(key, props2.getProperty(key));
                sce.getServletContext().setAttribute(key, props2.getProperty(key));
            }
        }
        catch (Exception e) {
        	log.error("SystemConfigInitListener.ERROR " + e.getMessage());
        }finally{
            if(is != null){
                try{
                    is2.close();
                }catch(Exception e){}
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

}
