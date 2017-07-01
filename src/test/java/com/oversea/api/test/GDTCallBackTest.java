package com.oversea.api.test;

import java.util.Date;

import org.junit.Test;

import com.oversea.common.util.HttpUtil;

public class GDTCallBackTest {
	
	@Test
	public void test() throws Exception {
		System.out.println(HttpUtil.doGet("http://localhost:8088/saveGdt?muid=c015e8709bae62a28463bcb83a7a6ae8&click_time="+new Date().getTime()/1000+"&appid=527012586&click_id=123123&app_type=ios&advertiser_id=469790"));
	}
	
}
