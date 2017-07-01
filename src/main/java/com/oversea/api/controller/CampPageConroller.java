package com.oversea.api.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oversea.common.config.CampPageItemConfig;
import com.oversea.common.manager.CampPageItemConfigManager;
import com.oversea.common.view.goods.GoodsAbridged;

@Controller
public class CampPageConroller {

    private static Log log = LogFactory.getLog(CampPageConroller.class);
    
    @Resource
    private CampPageItemConfigManager campPageItemConfigManager;


    /**手机客户端
     * @param request
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    @RequestMapping("/activity/{pageName}")
    public void process(HttpServletRequest request, 
    		HttpServletResponse response,
    		ModelMap map, 
    		@PathVariable String pageName) throws ServletException, IOException {
    	try {
    		CampPageItemConfig  campPageItemConfig = campPageItemConfigManager.getCampPageItemConfigByName(pageName);
    		if(campPageItemConfig==null){
    			log.error("活动页面专题未配置======="+pageName);
    			request.getRequestDispatcher("/404.jsp").forward(request, response);
    			return;
    		}
    		
    		request.setAttribute("pageTitle", campPageItemConfig.getPageTitle());
    		request.setAttribute("banner", campPageItemConfigManager.getCampPageBanner(campPageItemConfig.getBanner()));
    		request.setAttribute("comments", campPageItemConfig.getComments());
    		
    		List<GoodsAbridged> goodsAbridgedList =  campPageItemConfigManager.getCampPageGoodsList(campPageItemConfig.getProductIdList());
			request.setAttribute("goodsAbridgedList", goodsAbridgedList);
		} catch (Exception e) {
			log.error("跳转活动页面专题失败======="+pageName, e);
		}
    	
    	request.getRequestDispatcher("/camp.jsp").forward(request, response);
    }

    public static void main(String args[]) {
    	String str = "http://i.qichuang.com/0115070142d8bd5dc38c45f9a9aacb24ffa7ac1c.jpg@99q_1c_1e_250w.jpg";
    	System.out.println(str.length());
    	System.out.println(str.substring(0,66));
    }

}

