package com.oversea.api.core.spring.ioc;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;

public class ManagerMethodDynamicRegisterBean extends DynamicRegisterBean{

    @Override
    public BeanDefinitionBuilder builderBeanDefinition(BeanDefin beanDefin,BeanDefinitionBuilder builder) {
        builder.addPropertyReference("remoteServiceStrategy", "remoteServiceStrategy");
        builder.addPropertyValue("interfaceClazz", beanDefin.getInterfaceClazz());
        return builder;
    }
}
