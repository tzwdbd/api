package com.oversea.api.aide.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oversea.api.aide.ControlAider;
import com.oversea.api.core.OverSeaServiceScanner;
import com.oversea.api.exception.RequestProcessInterruptException;
import com.oversea.api.util.RC4Util;
import com.oversea.common.common.FileType;
import com.oversea.common.core.ServiceMethod;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.exception.ProcessStatusCode;
import com.oversea.common.request.MediaFileBaseParams;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.response.ResponseBaseParams;
import com.oversea.common.util.DateUtil;
import com.oversea.common.util.JSONUtil;
import com.oversea.common.util.Md5Encrypt;
import com.oversea.common.util.StringUtil;

import net.sf.json.JSONArray;

@SuppressWarnings("unchecked")
public class ControlAiderImpl implements ControlAider {
    
    public static final Log log = LogFactory.getLog(ControlAiderImpl.class);
    
    public static final String MD5_REQUEST_DATA_KEY	= "svUsBbxO4P1UFGHbLJPYLXps6yd6SRsj4EFKsUqNqq5cjZmgAg2rOgS0Xiw4JtJX";
    
    public static final String _3DES_SECRET = "CbvYYHo2oWGY1cKiytlijweK";
    public static final String OPERATION_KEY = "78d2204c48baa1c6e30fb3dc7ab61d1e2b414b6ec6f3fc3406566e90657453f6f4d5ea7f7a06a2d2a231f1bbf330445959dd6a0be8963ed5d8176f57992768be";
    public static final String REQUEST_DOMAIN = "com.oversea.common.request.";
    public static final byte	DECODE_FLAG	= 0x0001;
    public static final String	RC4_DECODE_KEY = "c1FwXaA10LCcRTPtSQPoKcwijlKoJOXPXnBWAGuQ62Z1T8CHuZZFhKCkoyLywVVBJs2FDcO20NXmM9HfwPr4m95Hv1OSi8RCZc5F1JWC5iHRRJ1X0CZsQLedKpL465RG";

    public static final List<String> ignoreResponseParams = Arrays.asList("instant","responsePublicParams","responseUpdateParams");

	private static final String IMG_TYPE = ",bmp,tif,tiff,cpx,dwg,eps,gif,ico,jiff,jpeg,jpg,pdf,pm5,png";
	
	private static final String VIDEO_TYPE = ",wmv,asf,asx,rm,rmvb,mpg,mpeg,mpe,dat,vob,dv,3gp,3g2,mov,avi,mkv,mp4,m4v,flv";
	
	private static final String AUDIO_TYPE = ",amr,m4a,aif,aiff,au,mp1,mp2,mp3,asx,m3u,pls,mlv,mpe,mpeg,mpg,mpv,mpa,ra,rm,ram,snd,wav,voc,ins,cda,cmf,mid,rmi,rcp,mod,s3m,xm,mtm,far,kar,it";
	
	private static final String TEXT_TYPE = ",txt,text";
    
	@Value("${oversea.temp.path}")
	private String tempPath = null;

	public void setTempPath(String tempPath) {
		if(!tempPath.endsWith("\\/") && !tempPath.endsWith(File.separator) && !tempPath.endsWith("\\\\") && !tempPath.endsWith("\\")){
			tempPath += File.separator;
		}
		this.tempPath = tempPath;
	}
    
    @Resource
    private OverSeaServiceScanner overSeaServiceScanner;
    
	/**
     * 校验[requestData]参数是否正确
     * @param paramStr
     * @param authKey
     * @return
     */
    private boolean checkRequestData(String paramStr , String authKey){
        return Md5Encrypt.md5(paramStr+MD5_REQUEST_DATA_KEY, "utf-8").equals(authKey);
    }

    /**
     * 将Map转换成bean
     * @param properties
     * @param object
     * @return
     * @throws Exception
     */
    private<T> Object copyMapToBean(Map<String,String> properties , Object object) throws Exception{
        Class<?> fieldType = null;
        ParameterizedType pt = null;
        Class<?> genericClazz = null;
        List<Field> fields = getFieldWithObject(object.getClass());
        for(Field f : fields){
            if(!properties.containsKey(f.getName()) || StringUtil.isEmpty(properties.get(f.getName()))){
                continue;
            }
            fieldType = f.getType();
            f.setAccessible(true);
            // 判断基础类型
            if(fieldType.isPrimitive() || fieldType.getName().startsWith("java.lang")){
                f.set(object, convertBaseType(fieldType.getSimpleName() , properties.get(f.getName())));
            // 处理时间类型
            }else if(fieldType.getName().endsWith("Date")){
                f.set(object, parseStringToDate(properties.get(f.getName())));
            // List类型
            }else if(fieldType.isAssignableFrom(List.class)){
                Type fc = f.getGenericType();
                if(fc == null){
                    continue;
                }
                if(fc instanceof ParameterizedType){
                    pt                  = (ParameterizedType) fc;  
                    genericClazz  = (Class<?>)pt.getActualTypeArguments()[0];
                    f.set(object, parseJsonArrayToList(properties.get(f.getName()) , genericClazz));
                }
            // nvshen Java Bean 类型
            }else if(fieldType.getName().startsWith("com.oversea.common")){
                f.set(object, parseJsonObjectToBean(properties.get(f.getName()) , fieldType));
            }else if(fieldType.getName().startsWith("com.oversea.api")){
                f.set(object, parseJsonObjectToBean(properties.get(f.getName()) , fieldType));
            }else{
                if(log.isWarnEnabled()){
                    log.warn("当前属性不被支持用于反射注入:["+fieldType.getName()+"]");
                }
            }
        }
        return object;
    }
    /**
     * 将字符串(数字)转换成时间对象
     * @param dateStr
     * @return
     */
    private Date parseStringToDate(String dateStr){
        Date date = null;
        Long dateLong = null;
        try{
        	try{
        		dateLong = Long.valueOf(dateStr);
        	}catch(NumberFormatException nfe){
        	}
            if(dateLong != null){
            	date = new Date(Long.valueOf(dateStr));
            }else{
            	date = DateUtil.ymdhmsString2DateTime(dateStr);
            }
        }catch(Exception e){
        	if(log.isWarnEnabled()){
                log.warn("当前时间格式不被支持用于反射 , 请转换成 long 格式的字符串 :["+dateStr+"]");
            }
        }
        return date;
    }
    /**
     * 获取当前类的字段信息
     * @param currentClass
     * @return
     */
    private List<Field> getFieldWithObject(Class<?> currentClass){
        List<Field>     fields              = new ArrayList<Field>();
        for(Field f : currentClass.getDeclaredFields()){
            fields.add(f);
        }
        if(currentClass.getSuperclass() != null){
            fields.addAll(getFieldWithObject(currentClass.getSuperclass()));
        }
        return fields;
    }
    
    /**
     * 将json转换成bean
     * @param json
     * @param genericClass
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    private<T> List<T> parseJsonArrayToList(String json, Class<T> genericClass) throws Exception{
        List<T>         beanList            = new ArrayList<T>();
        T                   genericObject   = null;
        JSONArray    jsonArray          = JSONArray.fromObject(json);
        for(int i = 0 ; i < jsonArray.size() ; i++){
            genericObject = (T)copyMapToBean(JSONUtil.json2MapFirstChildren(jsonArray.getString(i)) , genericClass.newInstance());
            beanList.add(genericObject);
        }
        return beanList;
    }
    
    private <T> Object parseJsonObjectToBean(String json, Class<T> targetClass) throws Exception{
        return (T)copyMapToBean(JSONUtil.json2MapFirstChildren(json) , targetClass.newInstance());
    }
    /**
     * 基础值类型转换
     * 
     * @param type 值类型 , 可以是 "String","Float","float","Double","double","Integer","int","Boolean","boolean"
     * @param value
     * @return
     */
    private Object convertBaseType(String type , String value){
    	if("null".equalsIgnoreCase(value)){
    		return null;
    	}
    	
        if(type.equals("Float") || type.equals("float")){
            return new Float(value);
        }
        if(type.equals("Double") || type.equals("double")){
            return new Double(value);
        }
        if(type.equals("Integer") || type.equals("int")){
            return new Integer(value);
        }
        if(type.equals("Long") || type.equals("long")){
            return new Long(value);
        }
        if(type.equals("Boolean") || type.equals("boolean")){
            return new Boolean(value);
        }
        if(type.equals("String")){
            return value;
        }
        return null;
    }
   

    @Override
    public void checkParamter(String body,String time, String sign) throws RequestProcessInterruptException{
        if(!checkRequestData(StringUtil.concatString(body,time), sign)) {
            throw new RequestProcessInterruptException(ProcessStatusCode.PARAM_CHECK_FAILD_ERROR);
        }
    }

    @Override
    public void compressOutputStream(byte[] bytes,OutputStream os) throws IOException{
        GZIPOutputStream gos = null;
        try{
            gos = new GZIPOutputStream(os);
            gos.write(bytes);
            gos.finish();
            gos.flush();
        }finally{
            if(gos != null){
                gos.close();
            }
        }
    }
    
    public byte[] compressToGzip(byte[] data)throws IOException {  
  	  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
  
        // 压缩  
        GZIPOutputStream gos = null;
        byte[] output = null;
		try {
			gos = new GZIPOutputStream(baos);
			gos.write(data, 0, data.length);  
			gos.finish();  
			output=baos.toByteArray();  
			baos.flush();  
		}finally{
            if(gos != null){
                gos.close();
            }
            if(baos!=null){
            	baos.close();  
            }
        }  
  
        return output;  
    }
    
    @Override
    public String createResultJson(ResponseBaseParams publicParams) {
    	return builderResultJson(publicParams);
    }
    
    /**
     * 拼凑返回数据
     * @param publicParams
     * @return
     */
    public static String builderResultJson(ResponseBaseParams publicParams){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                //忽略掉指定字段
                return ignoreResponseParams.contains(f.getName());
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        StringBuilder result = new StringBuilder();
        String operation = gson.toJson(publicParams);
        //拼凑数据结构
        publicParams.getResponsePublicParams().setInstant(publicParams.getInstant());
        publicParams.getResponsePublicParams().setUpdate(publicParams.getResponseUpdateParams());
        String _public = gson.toJson(publicParams.getResponsePublicParams());
        result.append("{").append("\"operation\":").append(operation).append(",").append("\"common\":").append(_public).append("}");
        return result.toString();
    }
    
    @Override
    public ResponseBaseParams appendRequestBaseParams(RequestBaseParams requestBaseParams , ResponseBaseParams responseBaseParams) throws RequestProcessInterruptException{
        if(responseBaseParams == null){
            responseBaseParams = new ResponseBaseParams();
        }
        //service业务层已经赋值
        if(!StringUtil.isEmpty(responseBaseParams.getResponsePublicParams().getStatus())){
        	return responseBaseParams;
        }
        responseBaseParams.getResponsePublicParams().setStatus(ProcessStatusCode.PROCESS_SUCCESS.getCode());
        responseBaseParams.getResponsePublicParams().setMemo(ProcessStatusCode.PROCESS_SUCCESS.getCodeMessage());
        if(StringUtil.isNotEmpty(requestBaseParams.getCookie())){
            responseBaseParams.getResponsePublicParams().setCookie(requestBaseParams.getCookie());
        }
        return responseBaseParams;
    }

	public static String toCaptial(String fieldName) {
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	public static String toLowerCaptial(String fieldName) {
		return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
	}
	
	public static String buildClassName(String operationType) {
		String requestDomainDirectory ="";
		String requestDomainName ="";
		if(operationType.lastIndexOf(".")>0) {
			requestDomainDirectory = operationType.substring(0,operationType.lastIndexOf(".")+1);
	    	requestDomainName = operationType.substring(operationType.lastIndexOf(".")+1);
		}else{
			requestDomainName=operationType;
		}

    	StringBuffer classNameBuffer = new StringBuffer();
    	classNameBuffer.append(REQUEST_DOMAIN)
    	               .append(requestDomainDirectory)
    	               .append(toCaptial(requestDomainName))
    	               .append("Request");
    	return classNameBuffer.toString();
	}
	
    @Override
    public RequestBaseParams generateRequestDomain(String operationType , Map<String,String> params) throws RequestProcessInterruptException{
        ServiceMethod oam = overSeaServiceScanner.getServiceByOperationType(operationType);
    	if(oam == null){
    		throw new RequestProcessInterruptException(ProcessStatusCode.PARAM_FORMAT_ERROR,"ServiceScanner 未找到 ["+operationType+"] 相应的service内方法 .");
    	}
        String		className	= buildClassName(operationType);
        Class<?>	clazz		= null;
        Object		object		= null;
        
        try {
            clazz = Class.forName(className);
            object	= clazz.newInstance();
            copyMapToBean(params , object);
        } catch (Exception e) {
            throw new RequestProcessInterruptException(ProcessStatusCode.PARAM_CHECK_FAILD_ERROR,e);
        }
        return (RequestBaseParams) object;
    }
    
	@Override
	public Map<String, String> paramterDecoder(HttpServletRequest request) throws OverseaException {
		String encrypt = request.getHeader("Encrypt");
		
		log.error("encrypt="+encrypt);
		// get方式请求
		if(StringUtil.isEmpty(encrypt) || encrypt.equals("0")){
			return parseGETParams(request);
		}
		// 以下代码为解析加密参数
		byte[]					bytes		= new byte[10240];
		ByteArrayOutputStream	os			= new ByteArrayOutputStream();
		InputStream				is			= null;
		String					paramterStr	= null;
		int						readLen		= 0;
		try{
			
			is = request.getInputStream();
			
			while((readLen = is.read(bytes)) != -1){
				os.write(bytes, 0, readLen);
			}
			
			bytes = os.toByteArray();
			bytes = decode(bytes);
			bytes = ungzip(bytes);
			
			paramterStr = new String(bytes,"UTF-8");
		}catch(Exception e){
			log.error("ERROR IN ControlAiderImpl paramterDecoder request query :"+request.getQueryString(),e );
			throw new OverseaException("请求参数解析异常:"+e);
		}
		if(!StringUtil.isEmpty(paramterStr)){
			Map<String,String> params = decodeParamterStrToMap(paramterStr);
			params.putAll(parseGETParams(request));
			return params;
		}else{
			return null;
		}
	}
	/**
	 * 获取通过get方式传入的参数
	 * @param request
	 * @return
	 */
	private Map<String,String> parseGETParams(HttpServletRequest request){
		Map<String,String>	params			= new HashMap<String,String>();
		Enumeration<String>	paramterNames	= request.getParameterNames();
		String				pName			= null;
		
		while(paramterNames.hasMoreElements()){
			pName = paramterNames.nextElement();
			params.put(pName, request.getParameter(pName));
		}
		return params;
	}
	
	private byte[] decode(byte[] params){
		return RC4Util.decry_RC4(params, RC4_DECODE_KEY);
	}
	
	private byte[] ungzip(byte[] params){
		ByteArrayOutputStream	os		= new ByteArrayOutputStream();
		GZIPInputStream			zis		= null;
		byte[]					buff	= new byte[params.length];
		int						readLen	= 0;
		try{
			try{
				zis = new GZIPInputStream(new ByteArrayInputStream(params));
				while ((readLen = zis.read(buff)) != -1) {
					os.write(buff, 0, readLen);
				}
			}finally{
				zis.close();
			}
		}catch(Exception e){
			log.error("unzip 发生异常:",e);
		}
		return os.toByteArray();
	}
	
	private Map<String,String> decodeParamterStrToMap(String paramterStr){
		Map<String,String>	paramsMap	= new HashMap<String,String>();
		String[]			params		= paramterStr.split("&");
		String				k			= null , v = null;
		for(String param : params){
			if(param.indexOf("=") > 0){
				k = param.substring(0, param.indexOf("="));
				v = param.substring(param.indexOf("=")+1);
				try{v = URLDecoder.decode(v,"UTF-8");}catch(Exception e){/*忽略*/}
				paramsMap.put(k, v);
			}
		}
		return paramsMap;
	}

	@Override
    public void clearFiles(RequestBaseParams requestParams) throws OverseaException {
		if(requestParams instanceof MediaFileBaseParams){
			MediaFileBaseParams	mediaRequest	= (MediaFileBaseParams) requestParams;
			File				tempFile		= null;
			
			if(mediaRequest.getAudioFiles() != null){
				for(String audio : mediaRequest.getAudioFiles()){
					tempFile = new File(audio);
					if (tempFile.isFile() && tempFile.exists()) {  
						 tempFile.delete();
					}
				}
			}
			
			if(mediaRequest.getVideoFiles() != null){
				for(String video : mediaRequest.getVideoFiles()){
					tempFile = new File(video);
					if (tempFile.isFile() && tempFile.exists()) {  
						 tempFile.delete();
					}
				}
			}
			
			if(mediaRequest.getImageFiles() != null){
				for(String image : mediaRequest.getImageFiles()){
					tempFile = new File(image);
					if (tempFile.isFile() && tempFile.exists()) {  
						 tempFile.delete();
					}
				}
			}
			
			if(mediaRequest.getTextFiles() != null){
				for(String image : mediaRequest.getTextFiles()){
					tempFile = new File(image);
					if (tempFile.isFile() && tempFile.exists()) {  
						 tempFile.delete();
					}
				}
			}
		}
    }
	
	@Override
	public void receiveFiles(RequestBaseParams requestParams, HttpServletRequest request) throws OverseaException {
		
		log.error("requestParams instanceof MediaFileBaseParams="+(requestParams instanceof MediaFileBaseParams));
		
		if(!(requestParams instanceof MediaFileBaseParams)){
			return;
		}
		if(StringUtil.isEmpty(tempPath)){
			return;
		}
		
		String subPath = null;
		int subUserId = new Random().nextInt(100);
		Map<FileType , String[]> files = null;
		
		if(subUserId < 10){
			subPath = "0"+subUserId;
		}else{
			subPath = String.valueOf(subUserId);
		}
		try {
			files = saveFile(request , tempPath + subPath);
		} catch (Exception e) {
			log.error("保存文件失败：",e);
			throw new OverseaException(ProcessStatusCode.FILE_RECEIVE_EXCEPTION);
		}
		if(files != null && files.size() > 0){
			MediaFileBaseParams media = (MediaFileBaseParams) requestParams;
			if(files.get(FileType.AUDIO) != null){
				media.setAudioFiles(files.get(FileType.AUDIO));
			}
			if(files.get(FileType.IMG) != null){
				media.setImageFiles(files.get(FileType.IMG));
			}
			if(files.get(FileType.VIDEO) != null){
				media.setVideoFiles(files.get(FileType.VIDEO));
			}
			if(files.get(FileType.TEXT) != null){
				media.setTextFiles(files.get(FileType.TEXT));
			}
		}
	}
	
	/**
	 * 保存上传的文件
	 * @param request	
	 * @param filePath	
	 * @throws Exception 
	 */
    private Map<FileType, String[]> saveFile(HttpServletRequest request, String filePath) throws Exception {
        Map<FileType, String[]> files=null;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置临时文件存储位置
			File file = new File(tempPath);
			ServletFileUpload upload = new ServletFileUpload(factory);

			files = new HashMap<FileType, String[]>();
			String suffix = null, relPath = null;
			FileType fileType = null;
			List<String> audio = new ArrayList<String>();
			List<String> video = new ArrayList<String>();
			List<String> img = new ArrayList<String>();
			List<String> text = new ArrayList<String>();
			
			// 设置内存缓冲区，超过后写入临时文件
			factory.setSizeThreshold(10240);
			// 创建文件夹
			if (!file.exists()) {
			    file.mkdirs();
			}
			factory.setRepository(file);
			// 设置单个文件的最大上传值
//			upload.setFileSizeMax(10240);
			// 设置整个request的最大值
//			upload.setSizeMax(1024000);
			upload.setHeaderEncoding("UTF-8");

			List<?> items = null;
			try {
			    items = upload.parseRequest(request);
			} catch (Exception e) {
			    log.error("error in saveFile method",e);
			    return files;
			}
			
			FileItem item = null;
			for (int i = 0; i < items.size(); i++) {
			    item = (FileItem) items.get(i);
			    suffix = FilenameUtils.getExtension(item.getName());
			    fileType = discernFileType(suffix);
			    if (fileType == null) {
			        continue;
			    }
			    File dir = new File(filePath);
			    if (!dir.exists()) {
			        dir.mkdirs();
			    }
			    //2015-07-20c7e9175b-92bd-4bb1-8ca3-196bfb0b9524长度为46位
			    //后面跟的是原始文件名
			    relPath = filePath + File.separator + DateUtil.ymdFormat(new Date()) + UUID.randomUUID().toString() +item.getName();
			    
			    log.error("isFormField=" + item.isFormField() + ",fileType=" + fileType + ",name=" + item.getName() + ",size=" + item.getSize() + ",relPath=" + relPath+",inMemory="+item.isInMemory());
			    
			    // 保存文件
			    if (!item.isFormField() && item.getName().length() > 0 && item.getSize() > 0) {
			        item.write(new File(relPath));
			    }
			    switch (fileType) {
			        case AUDIO: {
			            audio.add(relPath);
			            break;
			        }
			        case IMG: {
			            img.add(relPath);
			            break;
			        }
			        case VIDEO: {
			            video.add(relPath);
			            break;
			        }
			        case TEXT: {
			            text.add(relPath);
			            break;
			        }
			    }
			    if (audio.size() > 0) {
			        files.put(FileType.AUDIO, audio.toArray(new String[audio.size()]));
			    }
			    if (video.size() > 0) {
			        files.put(FileType.VIDEO, video.toArray(new String[video.size()]));
			    }
			    if (img.size() > 0) {
			        files.put(FileType.IMG, img.toArray(new String[img.size()]));
			    }
			    if (text.size() > 0) {
			        files.put(FileType.TEXT, text.toArray(new String[text.size()]));
			    }
			}
		} catch (Exception e) {
			log.error("ERROR IN ControlAiderImpl saveFile  method",e);
		}
        return files;
    }

    /**
	 * 判断文件类型
	 * @param suffix
	 * @return
	 */
	private FileType discernFileType(String suffix){
		if(StringUtil.isEmpty(suffix)){
			return null;
		}
		suffix = ","+suffix.toLowerCase();
		if(IMG_TYPE.indexOf(suffix) >= 0){
			return FileType.IMG;
		}
		if(VIDEO_TYPE.indexOf(suffix) >= 0){
			return FileType.VIDEO;
		}
		if(AUDIO_TYPE.indexOf(suffix) >= 0){
			return FileType.AUDIO;
		}
		if(TEXT_TYPE.indexOf(suffix) >= 0){
			return FileType.TEXT;
		}
		return null;
	}
	
	public static void main(String args[]) {
		
		String body = "{\"operation\":{\"method\":\"ht.ginza.login\",\"type\":\"wechat\",\"third_party_id\":\"owq0b0V8ZQZYB1Ncujb2QoCKOPHo\",\"third_party_unionid\":\"oaHwjt3x0m_9HtnnKy92nWhISjzM\",\"third_party_token\":\"Z7a5eOW6wRQmOHoNWqvwbg==\",\"third_party_avtar\":\"http://wx.qlogo.cn/mmopen/vi_32/4bMxOSRQ3pBqTP5R8UXduvjLQsmvbeWcvnRzLRdnlEBLb7IMN45OXMKdWdQIIHnicFyE5lnDepreZTXyNxewaug/0\",\"third_party_gender\":\"m\"},\"common\":{\"ua\":\"haihu/mp wechat/MicroMessenger Model/iPhone 6 pixelRatio/2 system/iOS 10.0.1 wechat/6.5.6 SDKVersion/1.2.1\",\"product_id\":\"mp\",\"product_version\":\"3.7\",\"time\":1495607643590,\"cookie\":\"gKIuBpf3CL0=\",\"partner_type\":\"wechat_mini_program\"}}";
		
System.out.println(Md5Encrypt.md5(StringUtil.concatString(body,1495607643590L)+MD5_REQUEST_DATA_KEY, "utf-8"));
        boolean ok =	Md5Encrypt.md5(StringUtil.concatString(body,1495607643590L)+MD5_REQUEST_DATA_KEY, "utf-8").equals("e171c5920cea78752be966aa919c0bbe");
        
        System.out.println(ok);
	}
}
