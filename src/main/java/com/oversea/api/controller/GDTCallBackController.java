package com.oversea.api.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.oversea.common.domain.tuiguang.GDTResult;
import com.oversea.common.domain.tuiguang.TuiguangGdtUser;
import com.oversea.common.enums.tuiguang.TuiguangStatus;
import com.oversea.common.manager.TuiguangManager;
import com.oversea.common.util.StringUtil;

@Controller
public class GDTCallBackController {
	private static Log log = LogFactory.getLog(GDTCallBackController.class);
	
	private final int advertiser_id = 1551243;
	private final int appid = 1022383035;
	
	@Resource
	private TuiguangManager tuiguangManager;

	@RequestMapping("saveGdt")
    public void saveGdt(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		
	    GDTResult result = new GDTResult();
		String advertiserId = request.getParameter("advertiser_id");
		String appId = request.getParameter("appid");
		
		String muid = request.getParameter("muid");
		String clickId = request.getParameter("click_id");
		String clickTime = request.getParameter("click_time");
		String osType = request.getParameter("app_type");

        log.error("[SaveGdt]execute start. advertiserId=" + advertiserId + ",appId=" + appId
                + ",muid=" + muid + ",clickId=" + clickId + ",clickTime=" + clickTime
                + ",osType=" + osType);
        try {
            if (!StringUtil.isEmpty(appId) && !StringUtil.isEmpty(advertiserId)
                    && Integer.valueOf(appId).equals(appid)
                    && Integer.valueOf(advertiserId).equals(advertiser_id)) {
                TuiguangGdtUser user = new TuiguangGdtUser();
                user.setClickId(clickId);
                user.setOsType(osType);
                user.setMuid(muid);
                user.setStatus(TuiguangStatus.INIT.getStatus());

                if (!StringUtil.isEmpty(clickTime)) {
                    // 这里的clickTime用于回传，是可选值，允许为空
                    Long time = null;
                    try {
                        time = Long.valueOf(clickTime);
                    } catch (Exception e) {
                    }
                    if (time != null) {
                        user.setClickTime(new Date(time * 1000));
                    }
                }
                tuiguangManager.addTuiguangGdtUser(user);
                result.setRet(0);
                result.setMsg("成功");
            } else {
                log.error("[SaveGdt]execute fail. advertiserId=" + advertiserId + ",appId is " + appId);
                result.setRet(-1);
                result.setMsg("参数错误");
            }
        } catch (Exception e) {
            log.error("[SaveGdt]execute error. advertiserId=" + advertiserId + ",appId is " + appId + ",error=" + e.getMessage(), e);
            result.setRet(-1);
            result.setMsg("参数错误");
        }
        try {
            response.getWriter().print(new Gson().toJson(result));
        } catch (IOException e) {
        }
	}

}
