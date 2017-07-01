package com.oversea.api.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.api.annotation.DBResource;
import com.oversea.api.util.WorkBookUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-*.xml" })
@DBResource("Test.xls")
public class DemoTestCase {
	@BeforeClass
	public static void init() throws Exception {
		WorkBookUtils.initDBResource(DemoTestCase.class);
	}
	

	@AfterClass
	public static void tearDown() throws Exception {
		WorkBookUtils.clearDBResource(DemoTestCase.class);
	}
	
	@Test
	public void 这是一个测试用例(){
		System.out.println("This is a demo");
	}
}
