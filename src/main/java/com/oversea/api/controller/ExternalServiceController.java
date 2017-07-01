package com.oversea.api.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.oversea.api.util.DBHelper;
import com.oversea.common.client.tenpay.MD5Util;
import com.oversea.common.enums.PartnerType;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.manager.UserTradeManager;
import com.oversea.common.util.DateUtil;
import com.oversea.common.util.StringUtil;

@Controller
public class ExternalServiceController {

	
	private static Log log = LogFactory.getLog(ExternalServiceController.class);
	
	@Resource
	private UserTradeManager userTradeManager;
	
	/**
	 * 家政帮订单数据，返回今日订单量，昨日订单量，总订单量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws OverseaException 
	 * @throws SQLException 
	 */
	 @RequestMapping("/jzb/orderInfo")
	    public void jzbOrderInfo(HttpServletRequest request, 
	    		HttpServletResponse response) throws ServletException, IOException, OverseaException, SQLException {
	    	String partnerId = request.getParameter("partnerId");
	    	String sign = request.getParameter("sign");
	    	PartnerType pt = PartnerType.JIAZB;
	    	if(!pt.getPartnerId().equals(partnerId)){
	    		sendOutJson(response, "partnerId不正确");
	    	}else{
	    		String md5Sign = MD5Util.MD5Encode(partnerId+pt.getMd5Key(), "utf-8").toLowerCase();
	    		if(md5Sign.equals(sign)){
	    			List<JzbBean> jbList = new ArrayList<ExternalServiceController.JzbBean>();
	    			Date now = new Date();
	    			Date yesterday = DateUtil.increaseDay(now, -1);
	    			Map<String, Object> todayMap = userTradeManager.getOrderInfoByDateAndPartnerId(partnerId, now);
	    			JzbBean todayInfo = new JzbBean();
	    			todayInfo.setTime(DateUtil.ymdFormat(now));
	    			todayInfo.setCount(todayMap.get("count")==null?"0":todayMap.get("count").toString());
	    			todayInfo.setGmv(todayMap.get("gmv")==null?"0":todayMap.get("gmv").toString());
	    			this.setPVUV(todayInfo, todayInfo.getTime());
	    			Map<String, Object> yesMap = userTradeManager.getOrderInfoByDateAndPartnerId(partnerId, yesterday);
	    			JzbBean yesInfo = new JzbBean();
	    			yesInfo.setTime(DateUtil.ymdFormat(yesterday));
	    			yesInfo.setCount(yesMap.get("count")==null?"0":yesMap.get("count").toString());
	    			yesInfo.setGmv(yesMap.get("gmv")==null?"0":yesMap.get("gmv").toString());
	    			this.setPVUV(yesInfo, yesInfo.getTime());
	    			Map<String, Object> allMap = userTradeManager.getOrderInfoByDateAndPartnerId(partnerId, null);
	    			JzbBean allInfo = new JzbBean();
	    			allInfo.setTime("全部");
	    			allInfo.setCount(allMap.get("count")==null?"0":allMap.get("count").toString());
	    			allInfo.setGmv(allMap.get("gmv")==null?"0":allMap.get("gmv").toString());
	    			this.setPVUV(allInfo, null);
	    			jbList.add(todayInfo);
	    			jbList.add(yesInfo);
	    			jbList.add(allInfo);
	    			sendOutJson(response, jbList);
	    		}else{//加密错误
	    			sendOutJson(response, "加密结果不正确");
	    		}
	    	}
	    }
	 
	 protected void sendOutJson(HttpServletResponse response,
				Object responseValue) throws IOException {
			Gson gson = new Gson();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			 //允许跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(
					responseValue == null ? "" : gson.toJson(responseValue));
			response.flushBuffer();
		}
	 
	 
	 public void setPVUV(JzbBean jb,String time) throws SQLException{
		 String sql = "select pv, uv from bi.rpt_partner where rpt_date='"+time+"' and partner_name='家政帮客户端' and is_new_user='no'";
		 if(StringUtil.isEmpty(time)){
			 sql = "select pv, uv from bi.rpt_partner where  partner_name='家政帮客户端' and is_new_user='no'";
		 }
		 String pv = "0";
		 String uv = "0";
		 DBHelper dbh = null;
		 try{
		 dbh = new DBHelper(sql);
			ResultSet ret = dbh.pst.executeQuery();
			while (ret.next()) {
				pv = ret.getString(1);
				uv = ret.getString(2);
			}
			jb.setPv(pv);
			jb.setUv(uv);
		 }catch(Exception e){
			 log.error("setPVUV error",e);
		 }finally{
			 if(dbh!=null){
			 dbh.close();
			 }
		 }
	 }
	 
	 class JzbBean{
		 private String time;
		 private String count;
		 private String gmv;
		 private String pv;
		 private String uv;
		 
		public String getPv() {
			return pv;
		}
		public void setPv(String pv) {
			this.pv = pv;
		}
		public String getUv() {
			return uv;
		}
		public void setUv(String uv) {
			this.uv = uv;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getCount() {
			return count;
		}
		public void setCount(String count) {
			this.count = count;
		}
		public String getGmv() {
			return gmv;
		}
		public void setGmv(String gmv) {
			this.gmv = gmv;
		}
		 
	 }
	 
	 
	 
}
