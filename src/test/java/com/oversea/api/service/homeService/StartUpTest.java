package com.oversea.api.service.homeService;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.api.annotation.DBResource;
import com.oversea.api.util.WorkBookUtils;
import com.oversea.common.service.HomeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
@DBResource("StartUpTest.xls")
public class StartUpTest {
	
	@Resource
	private HomeService homeService;

    
    @BeforeClass
    public static void init() throws Exception {
        WorkBookUtils.initDBResource(StartUpTest.class);
    }
    
    @AfterClass
    public static void tearDown() throws Exception {
        WorkBookUtils.clearDBResource(StartUpTest.class);
    }
}
