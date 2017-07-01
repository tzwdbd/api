package com.oversea.api.core.remote.impl;

import com.oversea.api.core.remote.ServiceStrategy;
import com.oversea.common.core.ServiceProviderHandlerWapper;
import com.oversea.common.util.ClassUtil;

public class LocalServiceStrategy implements ServiceStrategy{

    @Override
    public Object getInstance(Class<?> interfaceClazz) {

        String beanName = ClassUtil.getDefaultBeanName(interfaceClazz);
        return ServiceProviderHandlerWapper.getInstance(interfaceClazz,beanName);
    }
}
