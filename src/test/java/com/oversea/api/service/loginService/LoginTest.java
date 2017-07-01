package com.oversea.api.service.loginService;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.common.jedis.JedisHelper;
import com.oversea.common.request.ht.ginza.LoginRequest;
import com.oversea.common.response.ht.ginza.LoginResponse;
import com.oversea.common.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
//@DBResource("LoginTest.xls")
public class LoginTest {
	
	@Resource
	private LoginService loginService;
	@Resource
	private JedisHelper jedisHelper;
	

//  @BeforeClass
//  public static void init() throws Exception {
//      WorkBookUtils.initDBResource(LoginTest.class);
//  }
//  
//
//  @AfterClass
//  public static void tearDown() throws Exception {
//      WorkBookUtils.clearDBResource(LoginTest.class);
//  }
	
  
  @Test
  public void test() throws Exception {
//	  String redisKey = "CAPTCHA"+"cookiecookiecookiecookiecookie";
//	  String captcha = "2315";
//	  jedisHelper.setObject(redisKey, captcha, 60*30);
	  
	  
	  LoginRequest request = new LoginRequest();
	  request.setChannel("AppStore");
//	  request.setCheck_code("2315");
	  request.setCookie("cookiecookiecookiecookiecookie");
	  request.setDevice_id("deviceIddeviceId");
	  //request.setIfa("ifa-ifa-ifa-ifa-ifa");
	  request.setMobile_no("15268833835");
	  request.setModel("ios");
	  request.setOs("8.2");
	  request.setProduct_id("iPhone");
	  request.setProduct_version("1.00");
//	  request.setPush_token(push_token);
	  request.setThird_party_avtar("http://q.qlogo.cn/qqapp/101020580/4001B642A4E6D1BDFF8AEFC9C37AE60E/100");
	  request.setThird_party_id("00002B8EACE8C54029B17E5272100E7A");
	  request.setThird_party_user_nick("泡沫∑");
	  request.setType("wechat");// mobile/wechat
	  
	  LoginResponse response = loginService.login(request);
  }

}
