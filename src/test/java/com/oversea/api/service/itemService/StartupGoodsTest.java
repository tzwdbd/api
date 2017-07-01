package com.oversea.api.service.itemService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.api.dao.RequestStatisticsRecordDAO;
import com.oversea.api.domain.RequestStatisticsRecord;
import com.oversea.api.mongo.ApiMongoTemplete;
import com.oversea.common.request.RequestBaseParams;
import com.oversea.common.request.ht.ginza.StartupGoodsRequest;
import com.oversea.common.response.ht.ginza.StartupGoodsResponse;
import com.oversea.common.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
//@DBResource("StartupGoodsTest.xls")
public class StartupGoodsTest {
	
	@Resource
	private ItemService itemService;
	@Resource
	private RequestStatisticsRecordDAO requestStatisticsRecordDAO;
	@Resource
	private ApiMongoTemplete apiMongoTemplete;

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
    	StartupGoodsRequest request = new StartupGoodsRequest();
    	request.setPage_no(1);
    	StartupGoodsResponse reponse = itemService.startupGoods(request);
    }
    
    @Test
	public void ss(){
		List<RequestStatisticsRecord> list = new ArrayList<RequestStatisticsRecord>();
		RequestStatisticsRecord v = new RequestStatisticsRecord();
		v.setMethod("my.ss2");
		RequestBaseParams r = new RequestBaseParams();
		r.setUser_id(22L);
		v.setRequestBaseParams(r);
		list.add(v);
		RequestStatisticsRecord v1 = new RequestStatisticsRecord();
		RequestBaseParams r1 = new RequestBaseParams();
		r1.setUser_id(22L);
		v1.setRequestBaseParams(r1);
		v1.setMethod("my.ss2");
		list.add(v1);
		apiMongoTemplete.insertAll(list);
		//requestStatisticsRecordDAO.insert(v1);
		System.out.println("This is a demo");
	}

}
