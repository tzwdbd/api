package com.oversea.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.oversea.common.exception.OverseaException;
import com.oversea.common.exception.ProcessStatusCode;
import com.oversea.common.manager.ResourcesManager;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;
import com.oversea.common.util.JSONUtil;
import com.oversea.common.util.StringUtil;

@Controller
public class MainController {

    private static Log log = LogFactory.getLog("MAIN_CONTROLER");

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
    @RequestMapping("/service")
    public void process(HttpServletRequest request, HttpServletResponse response) {
        String requestData = null;
        String sign = null;
        String resultJson = null;
        String operationType = null;
        
        Map<String, String> allParams = null;
        Map<String, String> publicParams = null;
        Map<String, String> operationParams = null;
        Map<String, String> deviceParams = null;
        RequestBaseParams operationRequest = null;
        ResponseBaseParams operationResponse = null;
        ServletOutputStream os = null;
        try {

            // step1 解析参数
            Map<String, String> params = controlAider.paramterDecoder(request);
            if(params==null||params.size()==0){
               log.error("MainController process  method "+request.getParameterMap()+"====>request method "+request.getHeader("method_api")+"====> test "+request.getHeader("User-Agent"));
               return;
            }
            requestData = params.get("requestData");
            sign = params.get("sign");

            if (log.isDebugEnabled()) {
                log.debug(String.format("requestData=[%s]", requestData));
            }

            allParams       = JSONUtil.json2MapFirstChildren(requestData);
            publicParams    = JSONUtil.json2MapFirstChildren(allParams.get("common"));
            operationParams = JSONUtil.json2MapFirstChildren(allParams.get("operation"));
            deviceParams    = JSONUtil.json2MapFirstChildren(allParams.get("device"));
            
            operationType = operationParams.get("method");
            // step2 校验参数合法性
            controlAider.checkParamter(requestData, publicParams.get("time"), sign);
            
            // step3 根据客户端参数,生成domain
            operationParams.putAll(publicParams);
            operationParams.putAll(deviceParams);
            operationRequest = controlAider.generateRequestDomain(operationType, operationParams);
            
            //step4 接收文件
            controlAider.receiveFiles(operationRequest, request);
            
            //执行主方法
            operationResponse = systemOperationExecuter.execute(operationType, operationRequest);

            //ste5 清除文件
            if(!ApiConstant.METHOD_UPLOAD_PHOTO.equals(operationRequest.getMethod())){
            	controlAider.clearFiles(operationRequest);
            }
            
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
            String code = null;
            if (e instanceof InvocationTargetException) {
                Throwable throwable = ((InvocationTargetException) e).getTargetException();
                if (throwable instanceof OverseaException){
                    OverseaException exception = (OverseaException)throwable;
                    msg = exception.getMessage();
                    if(StringUtil.isBlank(msg)){
                    	msg = exception.getCode().getCodeMessage();
                    }
                    code = exception.getCode().getCode();
                    log.error("正常业务返回异常,错误信息[" + msg + "],method:[" + operationType + "]");
                }else{
                    msg = ProcessStatusCode.SYSTEM_ERROR.getCodeMessage();
                    code = ProcessStatusCode.getProcessStatusCodeByMessage(msg).getCode();
                    log.error("处理客户端请求发生非系统定义异常,错误信息[" + msg + "],method:[" + operationType + "]", throwable);
                }
            }
            //PRC异常
            else if(e instanceof RpcException){
                msg = ProcessStatusCode.RPC_EXCEPTION.getCodeMessage();
                code = ProcessStatusCode.getProcessStatusCodeByMessage(msg).getCode();
                log.error("处理客户端请求发生RPC异常,错误信息[" + msg + "],method:[" + operationType + "]", e);
            }
            else {
                msg = e.getMessage();
                code = ProcessStatusCode.getProcessStatusCodeByMessage(msg).getCode();
                log.error("处理客户端请求发生未知异常,错误信息[" + msg + "],method:[" + operationType + "]", e);
            }
            operationResponse = new ResponseBaseParams();
            operationResponse.getResponsePublicParams().setStatus(code);
            operationResponse.getResponsePublicParams().setMemo(msg);
        }
        try {
            try {
                if (operationRequest != null) {
                    // 记录操作日志
                	Set<String> blackList = ApiConstant.clientBlackSet;
                	Map<String, Resources> map = resourcesManager.getSaleResourceByMap("clientBlackList");
                	
                	if(map.size() > 0) {
                		blackList = map.keySet();
                	}
                	
                	if(!blackList.contains(operationRequest.getMethod())) {
                	    logProcessResponseExecuter.log(operationRequest, operationResponse);
                	}
                	
                    // step8 将回写内容转换成json格式
                    resultJson = controlAider.createResultJson(operationResponse);
                    response.setHeader("Content-Encoding", "gzip");  
                    response.setContentType("application/json; charset=UTF-8");
                    controlAider.compressOutputStream(resultJson.getBytes("UTF-8"), response.getOutputStream());
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
}
