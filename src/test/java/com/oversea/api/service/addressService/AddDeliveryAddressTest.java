package com.oversea.api.service.addressService;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.common.exception.OverseaException;
import com.oversea.common.request.ht.ginza.AddDeliveryAddressRequest;
import com.oversea.common.response.ht.ginza.AddDeliveryAddressResponse;
import com.oversea.common.service.AddressService;
import com.oversea.common.view.address.DeliveryAddress;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
//@DBResource("StartUpTest.xls")
public class AddDeliveryAddressTest {
	
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
    	AddDeliveryAddressRequest request = new AddDeliveryAddressRequest();
    	request.setUser_id(123456L);
    	
    	DeliveryAddress delivery_address = new DeliveryAddress();
    	delivery_address.setAddress("西斗门路9号");
    	delivery_address.setState("浙江省");
    	delivery_address.setCity("杭州市");
    	delivery_address.setDistrict("西湖区");
    	delivery_address.setZip_code("310000");
//    	delivery_address.setIs_default(is_default);
    	delivery_address.setMobile("13423456789");
    	delivery_address.setReceiver("万淘粉");
    	
    	request.setDelivery_address(delivery_address);
    	AddDeliveryAddressResponse response = addressService.addDeliveryAddress(request);
    }

}
