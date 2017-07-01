package com.oversea.api.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.oversea.api.util.CheckMobile;
import com.oversea.api.util.CookieHelper;
import com.oversea.common.constant.SSY;
import com.oversea.common.domain.goods.Product;
import com.oversea.common.domain.groupbuy.GroupProductPackage;
import com.oversea.common.domain.qrcode.MappingProductIdCode;
import com.oversea.common.domain.qrcode.MappingUserIdCode;
import com.oversea.common.domain.ssy.SSYProduct;
import com.oversea.common.domain.ssy.SSYTag;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.exception.ProcessStatusCode;
import com.oversea.common.manager.CampPageItemConfigManager;
import com.oversea.common.manager.SSYManager;
import com.oversea.common.request.ht.ginza.OnePunchRequest;
import com.oversea.common.response.ht.ginza.OnePunchResponse;
import com.oversea.common.util.QRCodeUtil;
import com.oversea.common.view.ssy.BondedGoodsEntity;
import com.oversea.common.view.ssy.GlobalGoodsEntity;
import com.oversea.common.view.ssy.GlobalTagGoodsEntity;
import com.oversea.common.view.ssy.OnePunchEntity;
import com.oversea.common.view.ssy.OnePunchGoodsEntity;
import com.oversea.common.view.ssy.TopGoodsEntity;

@Controller
public class SSYController {

	 @Resource
	 private SSYManager sSYManager;
	
	 @RequestMapping("/1111")
	 public String newIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 long userId = CookieHelper.getUserId(request);

	        String userAgent = request.getHeader("User-Agent");
	        boolean isPhone = CheckMobile.check(userAgent);
	        if(!isPhone){
	        	if (userId == -1) {
	                response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	                String jump = URLEncoder.encode("jump={\"pk\":\"3wTf8Auth\"}","UTF-8");
	                response.addHeader("Location",  "http://www.taofen8.com/haihu/login?"+jump);
	                return null;
	            }
	        }
	       
	        
	        List<OnePunchEntity> oPEList = sSYManager.queryOnePunchList(isPhone);
	        
	        MappingUserIdCode mUIC = sSYManager.getMappingUserIdCodeByRuId(userId);

	        if (mUIC != null) {
	            for (int i = 0; i < oPEList.size(); ++i) {
	                OnePunchEntity oPE = oPEList.get(i);
	                for (int j = 0; j < oPE.getGoods_list().size(); ++j) {
	                    OnePunchGoodsEntity oPGE = oPE.getGoods_list().get(j);
	                    MappingProductIdCode mPIC = sSYManager.getMappingProductIdCodeByRpId(Long.parseLong(oPGE.getPackage_id()));
	                    if (mPIC != null) {
	                        oPGE.setBarcode(QRCodeUtil.generatorFilePath(mPIC.getVirtualProductId(), mUIC.getVirtualUserId()));
	                    }
	                }
	            }
	        }
	        
	        OnePunchResponse rep = new OnePunchResponse();
			
			rep.setOne_punch_list(oPEList);
			
	        if (rep != null) {
	            String json = new Gson().toJson(rep);
	            request.setAttribute("__onePunchRep", json);
	        }
	        
	        List<SSYProduct> sSYPList = sSYManager.getProductList();
	        List<TopGoodsEntity> tGEList = new ArrayList<TopGoodsEntity>();
	        List<GlobalTagGoodsEntity> gTGEList = new ArrayList<GlobalTagGoodsEntity>();
	        List<BondedGoodsEntity> bGEList = new ArrayList<BondedGoodsEntity>();
	        Map<String, List<GlobalGoodsEntity>> gGEMap = new HashMap<String, List<GlobalGoodsEntity>>();
	
	        for (int i = 0; i < sSYPList.size(); ++i) {
	            SSYProduct p = sSYPList.get(i);
	
	            if (p.getZone() == SSY.ZONE_TOP_GOODS) {
	            	
	            	TopGoodsEntity tGE = sSYManager.product2TopGoodsEntity(p);
	            	if (tGE == null) continue;
	                tGEList.add(tGE);
	                
	            } else if (p.getZone() == SSY.ZONE_GLOBAL_GOODS) {
	                List<GlobalGoodsEntity> gGEList = gGEMap.get(String.valueOf(p.getTagId()));
	                if (null == gGEList) {
	                    gGEList = new ArrayList<GlobalGoodsEntity>();
	                    gGEMap.put(String.valueOf(p.getTagId()), gGEList);
	                }
	                
	                GlobalGoodsEntity gGE = sSYManager.product2GlobalGoodsEntity(p);
	                if (gGE == null) continue;
	                
	                gGEList.add(gGE);
	
	            } else if (p.getZone() == SSY.ZONE_BONDED_GOODS) {
	            	
	            	BondedGoodsEntity bGE = sSYManager.product2BondedGoodsEntity(p);
	            	if(bGE == null) continue;
	            	
	                bGEList.add(bGE);
	            }
	        }
	
	        List<SSYTag> tagList = sSYManager.queryTagList();
	
	        for (int i = 0; i < tagList.size(); ++i) {
	            SSYTag tag = tagList.get(i);
	
	            List<GlobalGoodsEntity> gGEList = gGEMap.get(tag.getId());
	            GlobalTagGoodsEntity gTGE = new GlobalTagGoodsEntity();
	
	            gTGE.setGoods_list(gGEList);
	            gTGE.setTag_id(String.valueOf(tag.getId()));
	            gTGE.setTag_name(tag.getName());
	            gTGEList.add(gTGE);
	        }
	
	        request.setAttribute("globalTagGoodsList", gTGEList);
	        request.setAttribute("topGoodsList", tGEList);
	        request.setAttribute("bondedGoodsList", bGEList);
	        
	    	
	    	return "html/1111";
	    	
	 }
	 
	 
	
}
