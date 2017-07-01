package com.oversea.api.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.rpc.RpcException;
import com.oversea.api.aide.ControlAider;
import com.oversea.api.core.SystemOperationExecuter;
import com.oversea.api.exception.RequestProcessInterruptException;
import com.oversea.api.execute.LogProcessResponseExecuter;
import com.oversea.api.util.ApiConstant;
import com.oversea.common.domain.Resources;
import com.oversea.common.encode.ThreeDES;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.exception.ProcessStatusCode;
import com.oversea.common.manager.ResourcesManager;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;
import com.oversea.common.util.JSONUtil;
import com.oversea.common.util.StringUtil;

@Controller
public class H5Controller {

    private static Log log = LogFactory.getLog("H5_CONTROLER");

    @Resource
    private SystemOperationExecuter systemOperationExecuter;

    @Resource
    private LogProcessResponseExecuter logProcessResponseExecuter;

    @Resource
    private ControlAider controlAider;
    
    @Resource
    private ResourcesManager resourcesManager;
    
    /**手机客户端
     * @param request
     * @param response
     */
    @RequestMapping("/h5")
    public void process(HttpServletRequest request, HttpServletResponse response) {
    	
        String requestData = null;
        String sign = null;
        String resultJson = null;
        String operationType = null;
        
        Map<String, String> allParams = null;
        Map<String, String> publicParams = null;
        Map<String, String> operationParams = null;
        RequestBaseParams operationRequest = null;
        ResponseBaseParams operationResponse = null;
        ServletOutputStream os = null;
        try {
        	
        	requestData = request.getParameter("requestData");
        	sign        = request.getParameter("sign");
        	
        	//decode
        	try{
        		requestData = URLDecoder.decode(requestData,"UTF-8");
        		sign        = URLDecoder.decode(sign,"UTF-8");
        	}catch(Exception e){}
        	

            allParams       = JSONUtil.json2MapFirstChildren(requestData,request.getParameter("requestData"));
            publicParams    = JSONUtil.json2MapFirstChildren(allParams.get("common"));
            operationParams = JSONUtil.json2MapFirstChildren(allParams.get("operation"));
            
            operationType = operationParams.get("method");
            // step2 校验参数合法性
            controlAider.checkParamter(requestData, publicParams.get("time"), sign);
            
            // step3 根据客户端参数,生成domain
            operationParams.putAll(publicParams);
            operationRequest = controlAider.generateRequestDomain(operationType, operationParams);
            
            //step4 接收文件
            controlAider.receiveFiles(operationRequest, request);
            //用户id解密
            if(StringUtil.isNotEmpty(operationRequest.getCookie())) {
            	 Long userId = Long.valueOf(ThreeDES.decryptMode(operationRequest.getCookie()));
            	 operationRequest.setUser_id(userId);
            }
            operationRequest.setIp(getIpAddress(request));
            //执行主方法
            operationResponse = systemOperationExecuter.execute(operationType, operationRequest);
            
            //用户加密
            if(operationRequest.getUser_id()!=null && operationRequest.getUser_id()>0) {
            	String cookie = ThreeDES.encryptMode(String.valueOf(operationRequest.getUser_id()).getBytes("UTF-8"));
            	operationRequest.setCookie(cookie);
            }

            //ste5 清除文件
            controlAider.clearFiles(operationRequest);
            // step6 补充返回信息(写入公共参数)
            operationResponse = controlAider.appendRequestBaseParams(operationRequest, operationResponse);
        } catch (RequestProcessInterruptException rpie) {
            operationResponse = new ResponseBaseParams();
            operationResponse.getResponsePublicParams().setStatus(rpie.getCode().getCode());
            operationResponse.getResponsePublicParams().setMemo(rpie.getCode().getCodeMessage());
            log.error("处理客户端请求发生异常,错误信息[" + rpie.getMessage() + "],method:[" + operationType + "]", rpie);
        }
        catch (Exception e) {
            //异常处理
            String msg = null;
            if (e instanceof InvocationTargetException) {
                Throwable throwable = ((InvocationTargetException) e).getTargetException();
                if (throwable instanceof OverseaException){
                    msg = throwable.getMessage();
                    log.error("正常业务返回异常,错误信息[" + msg + "],method:[" + operationType + "]");
                }else{
                    msg = ProcessStatusCode.SYSTEM_ERROR.getCodeMessage();
                    log.error("处理客户端请求发生非系统定义异常,错误信息[" + msg + "],method:[" + operationType + "]", e);
                }
            }
            //PRC异常
            else if(e instanceof RpcException){
                msg = ProcessStatusCode.RPC_EXCEPTION.getCodeMessage();
                log.error("处理客户端请求发生RPC异常,错误信息[" + msg + "],method:[" + operationType + "]", e);
            }
            else {
                msg = e.getMessage();
                log.error("处理客户端请求发生未知异常,错误信息[" + msg + "],method:[" + operationType + "]", e);
            }
            operationResponse = new ResponseBaseParams();
            operationResponse.getResponsePublicParams().setStatus(ProcessStatusCode.getProcessStatusCodeByMessage(msg).getCode());
            operationResponse.getResponsePublicParams().setMemo(msg);
            operationResponse.getResponsePublicParams().setUser_id(String.valueOf(operationRequest.getUser_id()));

            
            
        }
        try {
            try {
                if (operationRequest != null) {
                	// String mongoEnv = System.getProperty("mongo.environment");
                	// 记录操作日志
                	Set<String> whiteList = ApiConstant.h5WhiteSet;
                	Map<String, Resources> map = resourcesManager.getSaleResourceByMap("h5WhiteList");
                	
                	if(map.size() > 0) {
                		whiteList = map.keySet();
                	}
                	
                	if(whiteList.contains(operationRequest.getMethod())) {
                		logProcessResponseExecuter.log(operationRequest, operationResponse);
                	}
                	
                    // step8 将回写内容转换成json格式
                    resultJson = controlAider.createResultJson(operationResponse);
                    response.setContentType("text/html; charset=UTF-8");
                    //允许跨域
                    response.setHeader("Access-Control-Allow-Origin", "*");
                    
                    String ua = operationRequest.getUa();
                    
                    if(StringUtils.isNotEmpty(ua) && ua.toLowerCase().indexOf("micromessenger") > -1
                    		&& StringUtils.isNotEmpty(resultJson)) {
                    	List<Resources> resourcesList = resourcesManager.getSaleResourceByType("domainUrl");
                    	resultJson = resultJson.replaceAll(resourcesList.get(1).getResValue(), resourcesList.get(0).getResValue());
                    	resultJson = resultJson.replaceAll(resourcesList.get(3).getResValue(), resourcesList.get(2).getResValue());
                    }
                    
            		response.getWriter().print(resultJson);
                }
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        } catch (Exception e) {
            log.error("给客户端返回请求是发生异常:", e);
        }

    }
    
    public static String getIpAddress(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
          ip = request.getRemoteAddr(); 
        } 
        return ip; 
      } 
    
    public static void main(String args[]) throws UnsupportedEncodingException {
    	System.out.println(ThreeDES.encryptMode("1417684".getBytes("UTF-8")));
    	//System.out.println(ThreeDES.decryptMode("52678044ba7d73ae35d13ea051d0ff7e"));
    }
}
