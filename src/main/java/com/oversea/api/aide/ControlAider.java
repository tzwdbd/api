package com.oversea.api.aide;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oversea.api.exception.RequestProcessInterruptException;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;

/**
 * 辅助处理类
 * controller处理request时,提供对 数据参数验证 , 处理结果格式化等 操作.
 * 
 * @author yaohb
 *
 */
public interface ControlAider {

	/**
	 * 此方法将在request被提交至service处理之前被调用.
	 * @param body = {operation:{method:"",...},public:{cookie:"",user_id:"",time:"",...},device:{device_id:"",...}}
	 * @param time = public[time]
	 * @param sign md5(body+time+secret)
	 * @throws RequestProcessInterruptException
	 */
    public void checkParamter(String body,String time, String sign) throws RequestProcessInterruptException;
    /**
     * 对输出内容进行压缩 , 并输出至客户端
     * @param bytes
     * @param os
     * @return
     * @throws IOException 
     */
    public void compressOutputStream(byte[] bytes,OutputStream os) throws IOException;
    /**
     * 将处理结果封装成json
     * @param responseBaseParams
     * @return format:json
     */
    public String createResultJson(ResponseBaseParams responseBaseParams);
    /**
     * 附加服务端公共参数
     * @param requestBaseParams
     * @param responseBaseParams
     * @return
     */
    public ResponseBaseParams appendRequestBaseParams(RequestBaseParams requestBaseParams , ResponseBaseParams responseBaseParams) throws RequestProcessInterruptException;
    /**
     * 创建每个请求对应的domain对象
     * @param operationType
     * @param params
     * @return
     */
    public RequestBaseParams generateRequestDomain(String operationType , Map<String,String> params) throws RequestProcessInterruptException;
    /**
     * 对输入参数进行解码操作
     * @param request
     * @return
     * @throws OverseaException
     */
    public Map<String,String> paramterDecoder(HttpServletRequest request) throws OverseaException;
    
    /**
     * 接收文件
     * @param requestParams
     * @param request
     * @throws OverseaException
     */
    public void receiveFiles(RequestBaseParams requestParams, HttpServletRequest request) throws OverseaException;
    
    /**
     * 清除文件
     * @param requestParams
     * @param request
     * @throws OverseaException
     */
    public void clearFiles(RequestBaseParams requestParams) throws OverseaException;
    
    /**
     * gzip压缩
     * @param data
     * @throws IOException
     */
    public byte[] compressToGzip(byte[] data)throws IOException;
    
}
