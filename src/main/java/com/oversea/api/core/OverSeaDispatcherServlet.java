package com.oversea.api.core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.web.servlet.DispatcherServlet;

public class OverSeaDispatcherServlet extends DispatcherServlet{

    private static final long serialVersionUID = -8159082641579256195L;
    
    private static final String OVERSEA_REMOTE_TYPE="oversea.remote.type";
    
    private String remotetype = null;
    
    public void init(javax.servlet.ServletConfig config) throws javax.servlet.ServletException {
        String prefix = config.getServletContext().getRealPath("/");
        String file = config.getServletContext().getInitParameter("systemConfigLocation");
        file = config.getServletContext().getInitParameter("systemConfigLocationOverride");
        Properties props = new Properties();
        InputStream is = null;
        try {
            if (file.startsWith("file:")) {
                is = new FileInputStream(file.substring(5).replaceAll("\\$\\{user.home\\}", System.getProperty("user.home").replaceAll("\\\\", "/")));
            }
            else {
                String filePath = prefix + file;
                is = new FileInputStream(filePath);
            }
            props.load(is);
            remotetype = (String)props.get(OVERSEA_REMOTE_TYPE);
        }
        catch (Exception e) {
            System.out.println("SystemConfigInitListener.ERROR " + e.getMessage());
        }finally{
            if(is != null){
                try{
                    is.close();
                }catch(Exception e){}
            }
        }
        System.out.println("contextConfigLocation:"+remotetype);
        super.init(config);
    };
    
    @Override
    public void setContextConfigLocation(String contextConfigLocation) {
        if("remote".equalsIgnoreCase(remotetype)){
            super.setContextConfigLocation("classpath:/remote/*.xml,classpath:spring-*.xml,classpath:/spring/*.xml");
        }else if("local".equalsIgnoreCase(remotetype)){
            super.setContextConfigLocation( "classpath*:spring-*.xml,classpath*:/spring/*.xml");
        }
    }
}
