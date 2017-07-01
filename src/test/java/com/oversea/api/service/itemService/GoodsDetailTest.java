package com.oversea.api.service.itemService;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.common.exception.OverseaException;
import com.oversea.common.exception.ProcessStatusCode;
import com.oversea.common.request.ht.ginza.GoodsDetailRequest;
import com.oversea.common.response.ResponseBaseParams;
import com.oversea.common.response.ht.ginza.GoodsDetailResponse;
import com.oversea.common.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
//@DBResource("GoodsDetailTest.xls")
public class GoodsDetailTest {
	
	@Resource
	private ItemService itemService;
	

//    @BeforeClass
//    public static void init() throws Exception {
//        WorkBookUtils.initDBResource(StartUpTest.class);
//    }
//    
//
//    @AfterClass
//    public static void tearDown() throws Exception {
//        WorkBookUtils.clearDBResource(StartUpTest.class);
//    }
	
    
    @Test
    public void test() throws Exception {
    	try{
	    	GoodsDetailRequest request = new GoodsDetailRequest();
	    	request.setGoods_id(101010L);
	    	GoodsDetailResponse reponse = itemService.goodsDetail(request);
	    	System.out.println(reponse.toString());
    	}
    	catch (OverseaException e) {
            ProcessStatusCode.getProcessStatusCodeByMessage(e.getMessage()).getCode();
            e.getMessage();
        }
    } 
    
    

}
