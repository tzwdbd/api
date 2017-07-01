package com.oversea.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.gson.Gson;
import com.oversea.api.domain.BaseModel;
import com.oversea.api.domain.WebSpiderModel;
import com.oversea.api.enums.ApiCode;
import com.oversea.api.util.HttpUtil;
import com.oversea.api.util.MallProductCodeUtil;
import com.oversea.common.domain.Resources;
import com.oversea.common.domain.goods.Product;
import com.oversea.common.domain.mall.MallDefinition;
import com.oversea.common.enums.PcSpiderDealType;
import com.oversea.common.enums.goods.ProductStatus;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.manager.ActivityManager;
import com.oversea.common.manager.ItemManager;
import com.oversea.common.manager.MallDefinitionManager;
import com.oversea.common.manager.ProductManager;
import com.oversea.common.manager.ResourcesManager;
import com.oversea.common.util.Md5Encrypt;
import com.oversea.common.util.StringUtil;
import com.oversea.common.view.activity.MustLook;
import com.oversea.common.view.activity.MustLookPreferent;
import com.oversea.common.view.activity.PcTheme;
import com.oversea.common.view.goods.GoodsAbridged;

@RequestMapping("pc")
@Controller
public class WebSpiderConroller {

    private static Logger log = LoggerFactory.getLogger(WebSpiderConroller.class);
    
    public static final String REGEX_URL = "(http|https)://[\\s\\S]*";
    
    public static final String HAITAO_TASK_SERVER_URL_CONFIG_KEY = "oversea.api.haitaoTaskServerUrl";
    public static final String DEFAULT_HAITAO_TASK_SERVER_URL = "http://spiderauto.haihu.com/remote/pc";
    
    public static final String IMG_PRE = "http://img.haihu.com/";
    
    @Resource
    private ProductManager productManager;
    @Resource
    private MallDefinitionManager mallDefinitionManager;
    @Resource
    private ItemManager itemManager;
    @Resource
    private ActivityManager activityManager;
    @Resource
    private ResourcesManager resourcesManager;
    
    /**
     * 先将 url md5 后从 redis 查
     * 如果不存在或未上架，从 url 中解析 mallProductCode 从数据库查
     * 如果不存在或未上架，发给爬虫
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("search")
    @ResponseBody
    public Object search(HttpServletRequest request) throws Exception {
    	String callback = request.getParameter("callback");
    	String url = request.getParameter("url");
    	
    	if(StringUtils.isBlank(callback)) {
    		log.error("WebSpiderConroller_search: 未获取到callback");
    		return new JSONPObject(callback, new BaseModel("参数有误"));
    	}
    	
    	if(StringUtils.isBlank(url)) {
    		log.error("WebSpiderConroller_search: 未获取到url");
    		return new JSONPObject(callback, new BaseModel("请输入海外官网商品链接"));
    	}
    	
    	try {
    		if(!isUrl(url)) {
        		log.error("WebSpiderConroller_search: url格式有误 url={}", url);
        		return new JSONPObject(callback, new BaseModel("请输入正确的海外官网商品链接"));
        	}
    		
    		MallDefinition mall = null;
    		
			List<MallDefinition> mallList = mallDefinitionManager.getAllMallDefinition();
        	
        	if(mallList == null || mallList.size() == 0) {
        		log.error("WebSpiderConroller_search: 查询商城为空 url={}", url);
        		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
        	}
        	
        	for(MallDefinition mallTemp : mallList) {
        		String website = mallTemp.getSpiderWebsite();
				
				if(StringUtil.isBlank(website)) {
					continue;
				}
				
				if(url.indexOf(website) > -1) {
					mall = mallTemp;
					break;
				}
        	}
        	
        	if(mall == null) {
        		log.error("WebSpiderConroller_search: url未匹配到商城 url={}", url);
        		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_UNSUPPORT));
        	}
        	
        	if(mall.getSpiderSwitch().intValue() == 0) {
        		log.error("WebSpiderConroller_search: 商城不支持爬取 url={}, mallName={}", url, mall.getName());
        		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_UNSUPPORT));
        	}
        	
        	Integer pcSpiderDealType = PcSpiderDealType.NEW.getCode();
        	WebSpiderModel webSpiderModel = null;
        	Product product = null;
        	Long productId = null;
    		
    		String key = Md5Encrypt.md5(url, "utf-8");
    		String value = productManager.getValueFromRedisByKey(key);
    		
    		log.error("WebSpiderConroller_search: url={}, key={}, value={}", url, key, value);
    		
    		if(StringUtils.isNotEmpty(value)) {
    			if("THIRD_PART".equals(value) || "ADD_ON".equals(value)) {
    				productManager.clearValueFromRedisByKey(key);
    				pcSpiderDealType = PcSpiderDealType.EXIST_UNNORMAL.getCode();
    			} else if("ERROR".equals(value)) {
    				// 如果为 ERROR 情况，Redis 设置默认10分钟过期，有效期内不再爬取
    				log.error("WebSpiderConroller_search: redis error, url={}, redis_key={}", url, key);
    				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
    			} else {
    				productId = Long.parseLong(value);
    			}
    		}
    		
    		if(productId != null && productId.longValue() > 0) {
    			product = productManager.getProductById(productId);
    			
    			if(product == null) {
        			log.error("WebSpiderConroller_search: 商品不存在，从Redis中清除 url={}, key={}, productId={}", url, key, productId);
        			productManager.clearValueFromRedisByKey(key);
        		} else if(ProductStatus.NORMAL.getValue() != product.getStatus().intValue()) {
        			log.error("WebSpiderConroller_search: 商品未上架，从Redis中清除 url={}, key={}, productId={}", url, key, productId);
        			productManager.clearValueFromRedisByKey(key);
        			pcSpiderDealType = PcSpiderDealType.EXIST_UNNORMAL.getCode();
        		} else {
        			productManager.addPcSpiderLog(url, key, PcSpiderDealType.EXIST_NORMAL.getCode());
        			
        			webSpiderModel = new WebSpiderModel(ApiCode.CODE_SUCCESS);
        			webSpiderModel.setProductId(String.valueOf(product.getId()));
        			webSpiderModel.setMallName(mall.getName());
        			webSpiderModel.setMallCountry(mall.getCountry());
        			webSpiderModel.setMallLogo(IMG_PRE + mall.getIcon());
        			webSpiderModel.setProductTitle(product.getName());
        			webSpiderModel.setStatus(String.valueOf(product.getStatus()));
        			supplyProductInfo(webSpiderModel, product.getDefaultEntityId(),product.getId());
        			
        			if(webSpiderModel.getGoods() == null) {
        				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
        			}
        			
        			return new JSONPObject(callback, webSpiderModel);
        		}
    		}
        	
        	String mallName = mall.getName();
        	String spiderName = mall.getSpiderName();
        	String mallProductCode = MallProductCodeUtil.getCode(mallName, url);
        	
        	log.error("WebSpiderConroller_search: 获取mallProductCode url={}, mallProductCode={}, mallName={}", url, mallProductCode, mallName);
        	
        	if(StringUtils.isNotEmpty(mallProductCode)) {
        		product = productManager.getProductByMallIdAndMallProductCode(mall.getId(), mallProductCode);
        		
        		if(product == null) {
        			log.error("WebSpiderConroller_search: 未查询到商品 url={}, mallProductCode={}, mallName={}", url, mallProductCode, mallName);
        		} else if(ProductStatus.NORMAL.getValue() != product.getStatus().intValue()) {
        			log.error("WebSpiderConroller_search: 商品未上架 url={}, mallProductCode={}, mallName={}", url, mallProductCode, mallName);
        			pcSpiderDealType = PcSpiderDealType.EXIST_UNNORMAL.getCode();
        		} else {
        			productManager.addPcSpiderLog(url, key, PcSpiderDealType.EXIST_NORMAL.getCode());
        			
        			webSpiderModel = new WebSpiderModel(ApiCode.CODE_SUCCESS);
        			webSpiderModel.setProductId(String.valueOf(product.getId()));
        			webSpiderModel.setMallName(mall.getName());
        			webSpiderModel.setMallCountry(mall.getCountry());
        			webSpiderModel.setMallLogo(IMG_PRE + mall.getIcon());
        			webSpiderModel.setProductTitle(product.getName());
        			webSpiderModel.setStatus(String.valueOf(product.getStatus()));
        			supplyProductInfo(webSpiderModel, product.getDefaultEntityId(),product.getId());
        			
        			if(webSpiderModel.getGoods() == null) {
        				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
        			}
        			
        			return new JSONPObject(callback, webSpiderModel);
        		}
        	}
        	
        	String useSpider = "off"; // 是否使用爬虫，on-使用，off-不使用
        	String useSpiderMall = "amazon,amazon.jp,rei,";
        	Map<String, Resources> resMap = resourcesManager.getSaleResourceByMap("webSpiderType");
        	Resources useSpiderRes = resMap.get("useSpider");
        	
        	if(useSpiderRes != null && StringUtils.isNotEmpty(useSpiderRes.getResValue())) {
        		useSpider = useSpiderRes.getResValue();
        	}
        	
        	Resources useSpiderMallRes = resMap.get("useSpiderMall");
        	
        	if(useSpiderMallRes != null && StringUtils.isNotEmpty(useSpiderMallRes.getResValue())) {
        		useSpiderMall = useSpiderMallRes.getResValue();
        	}
        	
        	if("on".equalsIgnoreCase(useSpider)) {
        		log.error("WebSpiderConroller_search: url={}, spiderName={}, useSpiderMall={}", url, spiderName, useSpiderMall);
        		
        		if(useSpiderMall.indexOf(spiderName + ",") == -1) {
        			return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
        		}
        		
        		long pcSpiderId = productManager.addPcSpiderLog(url, key, pcSpiderDealType);
        		
            	String platform = spiderName;
            	
            	if("amazon.jp".equalsIgnoreCase(spiderName) || "amazon.uk".equalsIgnoreCase(spiderName) || "amazon.de".equalsIgnoreCase(spiderName)) {
            		platform = "amazon";
            	}
            	
            	Map<String, String> map = new HashMap<String, String>();
    			map.put("platform", platform);
    			map.put("url", url);
    			map.put("pc_spider_id", String.valueOf(pcSpiderId));

    			String requestUrl = System.getProperty(HAITAO_TASK_SERVER_URL_CONFIG_KEY);
    			if(StringUtil.isEmpty(requestUrl)){
    			    requestUrl = DEFAULT_HAITAO_TASK_SERVER_URL;
    			}
    			
    			log.error("WebSpiderConroller_search: 发送请求爬取 remoteUrl={}, url={}, platform={}, pc_spider_id={}", requestUrl, url, platform, pcSpiderId);
    			
    			String result = HttpUtil.postData(requestUrl, "data", new Gson().toJson(map));
    			
    			log.error("WebSpiderConroller_search: 爬取返回结果 remoteUrl={}, url={}, platform={}, pc_spider_id={}, result={}", requestUrl, url, platform, pcSpiderId, result);
    			
    			if(StringUtil.isBlank(result)) {
    				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
    			} else {
    				BaseModel baseModel = new Gson().fromJson(result, BaseModel.class);
    				
    				if(baseModel != null && "success".equals(baseModel.getMessage())) {
    					webSpiderModel = new WebSpiderModel(ApiCode.CODE_WEB_SPIDER_ANALYSIS);
            			webSpiderModel.setKey(Md5Encrypt.md5(url, "utf-8"));
    				} else {
    					return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
    				}
    			}
            	
    			return new JSONPObject(callback, webSpiderModel);
        	} else {
        		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
        	}
    	} catch(Exception e) {
    		log.error("WebSpiderConroller_search_error: url={}", url);
    		log.error("WebSpiderConroller_search_error: ", e);
    		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_ERROR));
    	}
    }
    
    private void supplyProductInfo(WebSpiderModel webSpiderModel, Long productEntityId,Long productId) throws OverseaException {
    	Map<String, String> productInfoMap = null;
		try {
			productInfoMap = productManager.getProductInfoById(productEntityId);
		} catch (OverseaException e) {
			log.error("WebSpiderConroller_supplyProductInfo_error: productEntityId={}", productEntityId);
    		log.error("WebSpiderConroller_supplyProductInfo_error: ", e);
		}
		
		if(productInfoMap != null) {
			webSpiderModel.setPrice(productInfoMap.get("price"));
			String productImage = productInfoMap.get("productImage");
			GoodsAbridged goodsAbridged = itemManager.getGlobalGroupGoods(productId);
			webSpiderModel.setGoods(goodsAbridged);
			if(StringUtils.isNotEmpty(productImage) && productImage.indexOf("http") == -1
					&& productImage.indexOf("https") == -1) {
				productImage = IMG_PRE + productImage;
			}
			
			webSpiderModel.setProductImage(productImage);
		}
    }
    
    @RequestMapping("check")
    @ResponseBody
    public Object check(HttpServletRequest request) throws Exception {
    	String callback = request.getParameter("callback");
    	String key = request.getParameter("key");
    	
    	if(StringUtils.isBlank(callback)) {
    		log.error("WebSpiderConroller_check: 未获取到callback");
    		return new JSONPObject(callback, new BaseModel("参数有误"));
    	}
    	
    	if(StringUtils.isBlank(key)) {
    		log.error("WebSpiderConroller_check: 未获取到key");
    		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
    	}
    	
    	try {
    		String value = productManager.getValueFromRedisByKey(key);
    		log.error("WebSpiderConroller_check: key={}, value={}", key, value);
    		Long productId = null;
    		
    		if(StringUtils.isNotEmpty(value)) {
    			if("THIRD_PART".equals(value)) {
    				productManager.clearValueFromRedisByKey(key);
    				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_THIRD_PART));
    			} else if("ADD_ON".equals(value)) {
    				productManager.clearValueFromRedisByKey(key);
    				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_ADD_ON));
    			} else if("ERROR".equals(value)) {
    				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
    			} else {
    				productId = Long.parseLong(value);
    			}
    		}
    		
    		if(productId == null || productId.longValue() == 0) {
    			log.error("WebSpiderConroller_check: 未查询到productId，重试 key={}", key);
    			return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_RETRY));
    		}
    		
    		Product product = productManager.getProductById(productId);
    		
    		if(product == null || ProductStatus.DISCARD.getValue() == product.getStatus().intValue()) {
    			log.error("WebSpiderConroller_check: 商品不存在，从Redis中清除 key={}, productId={}", key, productId);
    			productManager.clearValueFromRedisByKey(key);
    			return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
    		}
    		
    		MallDefinition mall = mallDefinitionManager.getMallDefinitionById(product.getMallId());
    		
    		if(mall == null) {
    			log.error("WebSpiderConroller_check: 未查询到商城信息 key={}, mallId={}", key, product.getMallId());
        		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
    		}
    		
    		if(ProductStatus.OUT_OF_STOCK.getValue() == product.getStatus().intValue()
    				|| ProductStatus.PENDING.getValue() == product.getStatus().intValue()) {
    			log.error("WebSpiderConroller_check: 商品未上架，从Redis中清除 key={}, productId={}", key, productId);
    			productManager.clearValueFromRedisByKey(key);
    			return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_OFF_SALE));
    		}
    		
    		WebSpiderModel webSpiderModel = new WebSpiderModel(ApiCode.CODE_SUCCESS);
			webSpiderModel.setProductId(String.valueOf(product.getId()));
			webSpiderModel.setMallName(mall.getName());
			webSpiderModel.setMallCountry(mall.getCountry());
			webSpiderModel.setMallLogo(mall.getIcon());
			webSpiderModel.setProductTitle(product.getName());
			webSpiderModel.setStatus(String.valueOf(product.getStatus()));
			supplyProductInfo(webSpiderModel, product.getDefaultEntityId(),product.getId());
			
			if(webSpiderModel.getGoods() == null) {
				return new JSONPObject(callback, new BaseModel(ApiCode.CODE_WEB_SPIDER_NO_RESULT));
			}
			
			return new JSONPObject(callback, webSpiderModel);
    	} catch(Exception e) {
    		log.error("WebSpiderConroller_check_error: key={}", key);
    		log.error("WebSpiderConroller_check_error: ", e);
    		return new JSONPObject(callback, new BaseModel(ApiCode.CODE_ERROR));
    	}
    }
    
    @RequestMapping("pcLoadIndex")
    @ResponseBody
    public Object index(HttpServletRequest request) throws Exception {
    	String callback = request.getParameter("callback");
    	Map<String,Object> map = new HashMap<String, Object>();
    	List<MustLookPreferent> all_must_look_list = activityManager.getAllMustLookList();
		List<MustLookPreferent> all_select_list = activityManager.getAllSelectList();
		map.put("all_must_look_list", all_must_look_list);
		map.put("all_select_list", all_select_list);
		return new JSONPObject(callback, map);
    	
    }
    
    @RequestMapping("pcThemeLoad")
    @ResponseBody
    public Object theme(HttpServletRequest request) throws Exception {
    	String callback = request.getParameter("callback");
    	Map<String,Object> map = new HashMap<String, Object>();
    	String theme_id = request.getParameter("theme_id");
		Long themeId = Long.parseLong(theme_id);
		List<MustLook> select_list = activityManager.getSelectList();
		List<PcTheme> theme_info_list = activityManager.getThemeInfoList(themeId);
		map.put("select_list", select_list);
		map.put("theme_info_list", theme_info_list);
		return new JSONPObject(callback, map);
    	
    }
    
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
}

