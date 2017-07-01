package com.oversea.api.service.userService;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.common.encode.ThreeDES;
import com.oversea.common.request.ht.ginza.CheckCodeRequest;
import com.oversea.common.response.ht.ginza.CheckCodeResponse;
import com.oversea.common.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
//@DBResource("GoodsDetailTest.xls")
public class CheckCodeTest {
	
	@Resource
	private UserService userService;


//  @BeforeClass
//  public static void init() throws Exception {
//      WorkBookUtils.initDBResource(CheckCodeTest.class);
//  }
//  
//
//  @AfterClass
//  public static void tearDown() throws Exception {
//      WorkBookUtils.clearDBResource(CheckCodeTest.class);
//  }
	
	@Test
    public void test() throws Exception {
		String mobile = "15268833835";
		
		CheckCodeRequest request = new CheckCodeRequest();
		request.setMobile_no(ThreeDES.encryptMode(mobile.getBytes()));
		
		CheckCodeResponse response = userService.checkCode(request);
	}

}
