package com.oversea.api.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RemoteReferenceConfig {

    /**
     * 注册中心地址
     */
    @Value("${dubbo.registry.address}")
    private String registryAddress;

    /**
     * 客户端连接数
     */
    @Value("${dubbo.connections:10}")
    private int connections;

    /**
     * 超时
     */
    @Value("${dubbo.timeout:15000}")
    private int timeout;

    /**
     * 重试
     */
    @Value("${dubbo.retries:0}")
    private int retries = 0;


    public static String SERVICE_EXPORT = "all";

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}