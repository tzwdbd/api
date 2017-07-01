package com.oversea.api.core;

import java.util.List;

import javax.annotation.Resource;

import com.oversea.api.core.remote.impl.RemoteServiceStrategy;
import com.oversea.api.core.spring.ManagerMethodReferenceFactoryBean;
import com.oversea.api.core.spring.ioc.DynamicRegisterBean.BeanDefin;
import com.oversea.api.core.spring.ioc.ManagerMethodDynamicRegisterBean;
import com.oversea.common.annotation.ManagerDefinition;
import com.oversea.common.constant.Constants;
import com.oversea.common.util.ClassUtil;

/**
 * 动态注册bean初始化
 * @author fengjian
 *
 */
public class OverSeaManagerInitialization{
    
    @Resource
    private ManagerMethodDynamicRegisterBean managerMethodDynamicRegisterBean;
    
    @Resource
    private RemoteServiceStrategy remoteServiceStrategy;
    
    /**
     * 获取ServiceDefinition注解信息
     * @param c
     * @return
     */
    public static ManagerDefinition getManagerDefinition(Class<?> c){
        return  c.getAnnotation(ManagerDefinition.class);
    }

    public void init() throws Exception {
        List<Class<?>> serviceInterfaceList = ClassUtil.getInterface(Constants.SCAN_MANAGER_PACKAGE,true);
        for(Class<?> serviceInterface:serviceInterfaceList){
            List<Class<?>> classlist = ClassUtil.getAllClassByInterface(serviceInterface);
            if(classlist==null || classlist.size()==0){
                continue;
            }
            
            Class<?> serviceClass = classlist.get(0);
            ManagerDefinition anno = getManagerDefinition(serviceClass);
            if(anno==null){
                continue;
            }
            BeanDefin beanDefin = new BeanDefin();
            beanDefin.setBeanClazz(ManagerMethodReferenceFactoryBean.class);
            beanDefin.setBeanName(ClassUtil.getDefaultBeanName(serviceClass));
            beanDefin.setInterfaceClazz(serviceInterface);
            managerMethodDynamicRegisterBean.registerBean(beanDefin);
        }
    }
}
