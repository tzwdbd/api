package com.oversea.api.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oversea.common.manager.UserManager;
import com.oversea.common.util.StringUtil;

@Controller
public class IndexController {
    private static Log log = LogFactory.getLog("MAIN_CONTROLER");
    
    @Resource
    private UserManager userManager;
    
	@RequestMapping("/")
	public void home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ua = ((HttpServletRequest)request).getHeader("User-Agent");
		if(isMobileBrowser(ua)){
			request.getRequestDispatcher("/indexWap.jsp").forward(request, response);
			return;
		}
		
		index(request, response);
	}
	
	@RequestMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	public static boolean isMobileBrowser(String ua){
		return !StringUtil.isEmpty(ua) 
				&& (ua.toLowerCase().indexOf("iphone") > 0
					||
					ua.toLowerCase().indexOf("android") > 0);
	}
	
	
//	param.setThird_party_id(request.getThird_party_id());
//	param.setThird_party_user_nick(request.getThird_party_user_nick());
//	param.setThird_party_avtar(request.getThird_party_avtar());
//	
//    @RequestMapping("/login/wx")
//    public void loginWx(HttpServletRequest request, HttpServletResponse response) {
//    	try{
//	    	String wxOpenId  = request.getParameter("wxOpenId");
//	    	String nick      = request.getParameter("nick");
//	    	String avtar      = request.getParameter("avtar");
//	    	
//	    	log.error(String.format("wxOpenId=[%s] nick=[%s] avtar=[%s]",wxOpenId,nick,avtar));
//	    	
//	    	wxOpenId = URLDecoder.decode(wxOpenId,"UTF-8");
//	    	nick      = URLDecoder.decode(nick,"UTF-8");
//	    	
//	    	//这里
//	    	User user = userManager.getUserByWeixinId(wxOpenId);
//	    	if(user == null) {
//	    		user = new User();
//				user.setWeixinId(wxOpenId);
//				user.setUserType("wechat");
//				user.setNick(nick);
//				user.setIcon(avtar);
//				Long userId = userManager.addUser(user);
//				user.setId(userId);
//	    		
//				WeixinUser wx = new WeixinUser();
//				wx.setUserId(user.getId());
//				wx.setWeixinUserId(wxOpenId);
//				wx.setHeadimgurl(avtar);
//				wx.setNick(nick);
//				userManager.addWeixinUser(wx);
//			}else{
//				//更新用户信息
//				user.setIcon(avtar);
//				user.setNick(nick);
//				userManager.updateUserById(user);
//			}
//	    	
//	    	request.setAttribute("uid", URLEncoder.encode(wxOpenId,"UTF-8"));
//	    	
//    	}catch(Exception e) {
//    		log.error("loginWx error"+e.getMessage(), e);
//    	}
//    }
//    
//    @RequestMapping("/login/tb")
//    public void loginAlipay(HttpServletRequest request, HttpServletResponse response) {
//    	try{
//	    	String tbUserIdStr = request.getParameter("tbUserId");
//	    	String nick        = request.getParameter("nick");
//	    	log.error(String.format("userIdStr=[%s] nick=[%s]",tbUserIdStr,nick));
//	    	
//	    	tbUserIdStr = URLDecoder.decode(tbUserIdStr,"UTF-8");
//	    	nick        = URLDecoder.decode(nick,"UTF-8");
//	    	
//	    	String tbUserId = ThreeDES.decryptMode(tbUserIdStr);
//	    	
//	    	User user = userManager.getUserByTbUserId(tbUserId);
//	    	if(user == null) {
//	    		user = new User();
//				user.setTbUserId(tbUserId);
//				user.setUserType("tb");
//				user.setNick(nick);
//				Long userId = userManager.addUser(user);
//				user.setId(userId);
//	    		
//				TbConnect tbConnect = new TbConnect();
//				tbConnect.setUserId(user.getId());
//				tbConnect.setNick(user.getNick());
//				tbConnect.setTbUserId(user.getTbUserId());
//			}else{
//				user.setNick(nick);
//				userManager.updateUserById(user);
//			}
//	    	
//	    	request.setAttribute("uid", URLEncoder.encode(tbUserIdStr,"UTF-8"));
//	    	
//	    	//request.getRequestDispatcher("/camp.jsp").forward(request, response);
//	    	
//    	}catch(Exception e) {
//    		log.error("loginAlipay error"+e.getMessage(), e);
//    	}
//    }
    
}
