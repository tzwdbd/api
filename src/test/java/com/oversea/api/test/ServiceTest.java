package com.oversea.api.test;


import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oversea.api.core.OverSeaServiceScanner;
import com.oversea.common.core.ServiceMethod;
import com.oversea.common.request.TestRequest;
import com.oversea.common.response.TestResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-*.xml","classpath*:/spring/*.xml","classpath:/remote/*.xml" })
public class ServiceTest {
    
    @Resource
    private OverSeaServiceScanner overSeaServiceScanner;

    @Test
    public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//        ServiceMethod bean = overSeaServiceScanner.getServiceByOperationType("test");
//        TestResponse response=(TestResponse) bean.getMethod().invoke(bean.getObject(), new TestRequest());
        //System.out.println(response.getMemo());
    }
}
