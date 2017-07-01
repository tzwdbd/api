package com.oversea.api.core.remote.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.rpc.cluster.support.FailfastCluster;
import com.oversea.common.provide.RemoteSeriveConfig;
import org.apache.log4j.Logger;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.oversea.api.core.RemoteReferenceConfig;
import com.oversea.api.core.remote.ServiceStrategy;

import javax.annotation.Resource;

public class RemoteServiceStrategy implements ServiceStrategy {

    private static final Logger log = Logger.getLogger(RemoteServiceStrategy.class.getName());

    /**
     * 缓存ReferenceConfig对象,用于destory
     */
    private static ConcurrentHashMap<String, ReferenceConfig<?>> servicesConfig = new ConcurrentHashMap<String, ReferenceConfig<?>>();

    /**
     * 缓存代理service proxy
     */
    private static ConcurrentHashMap<String, Object> services = new ConcurrentHashMap<String, Object>();

    private ApplicationConfig application = null;

    private RegistryConfig registry = null;

    @Resource
    private RemoteReferenceConfig remoteReferenceConfig;

    public void init() {
        application = new ApplicationConfig();
        try {
            application.setName("CLIENT_API_" + InetAddress.getLocalHost().getHostAddress().replace(".", ""));
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        }

        // 连接注册中心配置
        registry = new RegistryConfig();
        registry.setAddress(remoteReferenceConfig.getRegistryAddress());

    }

    public void destory() {
        for (String serviceInterface : services.keySet()) {
            servicesConfig.get(serviceInterface).destroy();
        }
        ProtocolConfig.destroyAll();
    }

    @Override
    public synchronized Object getInstance(Class<?> interfaceClazz) {
        try {
            if (!services.containsKey(interfaceClazz.getName())) {
                ReferenceConfig<Object> reference = new ReferenceConfig<Object>();
                reference.setApplication(application);
                reference.setRegistry(registry);
                reference.setInterface(interfaceClazz);
                reference.setVersion(RemoteSeriveConfig.VERSION);
                reference.setRetries(remoteReferenceConfig.getRetries());
                reference.setTimeout(remoteReferenceConfig.getTimeout());
                //默认使用快速失败
                reference.setCluster(FailfastCluster.NAME);
                Object service = reference.get();
                services.put(interfaceClazz.getName(), service);
                servicesConfig.put(interfaceClazz.getName(), reference);
                return service;
            } else {
                return services.get(interfaceClazz.getName());
            }
        } catch (Exception e) {
            log.error("matching remote service is error:" + e.getMessage(), e);
            return null;
        }
    }
}