package com.oversea.api.service.addressService;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.common.exception.OverseaException;
import com.oversea.common.request.ht.ginza.ModifyDeliveryAddressRequest;
import com.oversea.common.response.ht.ginza.ModifyDeliveryAddressResponse;
import com.oversea.common.service.AddressService;
import com.oversea.common.view.address.DeliveryAddress;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
//@DBResource("StartUpTest.xls")
public class ModifyDeliveryAddressTest {
	
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
    	ModifyDeliveryAddressRequest request = new ModifyDeliveryAddressRequest();
    	request.setUser_id(123456L);
    	
    	DeliveryAddress address = new DeliveryAddress();
    	address.setDelivery_address_id(1021L+"");
    	address.setCity("北京");
    	request.setDelivery_address(address);
    	
    	ModifyDeliveryAddressResponse response = addressService.modifyDeliveryAddress(request);
    }

}
