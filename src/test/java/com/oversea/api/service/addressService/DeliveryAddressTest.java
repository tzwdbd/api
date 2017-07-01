package com.oversea.api.service.addressService;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.api.service.homeService.StartUpTest;
import com.oversea.api.util.WorkBookUtils;
import com.oversea.common.exception.OverseaException;
import com.oversea.common.request.ht.ginza.DeliveryAddressRequest;
import com.oversea.common.response.ht.ginza.DeliveryAddressResponse;
import com.oversea.common.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
//@DBResource("StartUpTest.xls")
public class DeliveryAddressTest {
	
	@Resource
	private AddressService addressService;
	
//	
//	@BeforeClass
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
    public void test() throws OverseaException {
    	DeliveryAddressRequest request = new DeliveryAddressRequest();
    	request.setUser_id(123456L);
    	
    	DeliveryAddressResponse response = addressService.deliveryAddress(request);
    	request.setUser_id(123456L);
    }

}
