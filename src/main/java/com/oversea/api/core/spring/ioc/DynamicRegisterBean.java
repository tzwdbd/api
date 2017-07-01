package com.oversea.api.core.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 动态注册spring服务抽象类
 * @author fengjian
 *
 */
public abstract class DynamicRegisterBean implements ApplicationContextAware{
    
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    /**
     * 往spring容器注册
     * @param beanDefin
     */
    public void registerBean(BeanDefin beanDefin) {
        if (!applicationContext.containsBean(beanDefin.getBeanName())) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanDefin.getBeanClazz());
            beanDefinitionBuilder = builderBeanDefinition(beanDefin,beanDefinitionBuilder);
            beanDefinitionBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);
            registerSpringBean(beanDefin.getBeanName(), beanDefinitionBuilder.getRawBeanDefinition());
        }
    }
    
    public abstract BeanDefinitionBuilder builderBeanDefinition(BeanDefin beanDefin,BeanDefinitionBuilder builder);
 
    /**
     * @desc 向spring容器注册bean
     * @param beanName
     * @param beanDefinition
     */
    private void registerSpringBean(String beanName, BeanDefinition beanDefinition) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        BeanDefinitionRegistry beanDefinitonRegistry = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
        beanDefinitonRegistry.registerBeanDefinition(beanName, beanDefinition);
    }
    
    public static class BeanDefin{
        
        private String beanName;
        
        private Class<?> beanClazz;
        
        private Class<?> interfaceClazz;

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        public Class<?> getBeanClazz() {
            return beanClazz;
        }

        public void setBeanClazz(Class<?> beanClazz) {
            this.beanClazz = beanClazz;
        }

        public Class<?> getInterfaceClazz() {
            return interfaceClazz;
        }

        public void setInterfaceClazz(Class<?> interfaceClazz) {
            this.interfaceClazz = interfaceClazz;
        }
    }
}
