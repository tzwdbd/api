package com.oversea.api.core.spring;

import org.springframework.beans.factory.FactoryBean;

import com.oversea.api.core.remote.impl.RemoteServiceStrategy;

/**
 * 生成dubbo代理接口工厂
 * @author fengjian
 *
 */
public class ManagerMethodReferenceFactoryBean implements FactoryBean<Object>{
    
    /**
     * 是否启动manager 远程
     */
    public static boolean MANAGER_METHOD_REMOTE = false;

    private RemoteServiceStrategy remoteServiceStrategy;
    
    private Class<?> interfaceClazz;
    
    public void setRemoteServiceStrategy(RemoteServiceStrategy remoteServiceStrategy) {
        this.remoteServiceStrategy = remoteServiceStrategy;
    }

    public void setInterfaceClazz(Class<?> interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
    }

    @Override
    public Object getObject() throws Exception {
        return remoteServiceStrategy.getInstance(interfaceClazz);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClazz;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
