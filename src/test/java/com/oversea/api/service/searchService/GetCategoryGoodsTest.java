package com.oversea.api.service.searchService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.common.request.ht.ginza.GetCategoryGoodsRequest;
import com.oversea.common.service.SearchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/*.xml"})
public class GetCategoryGoodsTest {
	
	@Resource
	private SearchService searchService;
	
//	@BeforeClass
//    public static void init() throws Exception {
//	    for(String sql : clearSql) {
//		    DBUtils.execute(sql);
//	    }
//	   
//	    for(int i=0; i<100; i++){
//		    DBUtils.execute(initSql[0]);
//	    }
//    }
	
//	@AfterClass
//	public static void tearDown() throws Exception {
//	    for(String sql : clearSql) {
//		    DBUtils.execute(sql);
//	    }
//    }
	
	
	@Test
    public void test() throws Exception {
		GetCategoryGoodsRequest request = new GetCategoryGoodsRequest();
		request.setPage_no(2);
		request.setPage_size(5);
		searchService.getCategoryGoods(request);
		Assert.assertTrue(true);
	}
	
	
	
	//SQL
	private static String []initSql = {
		//"INSERT INTO sms_log (mobile, message, priority, create_time, status, product_id) VALUES (13022222222, '6727(醒醒验证码)您正在注册醒醒账号,为了保护您的账号安全,验证短信请勿转发其他人', 5, now(), 0, NULL)"
	};
	private static String []clearSql = {
//		"delete from sms_log where mobile='13011111111'"
//		,
//		"delete from sms_log where mobile='13022222222'"
	};

}
