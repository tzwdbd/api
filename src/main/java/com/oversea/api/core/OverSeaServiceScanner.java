package com.oversea.api.core;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.oversea.api.core.remote.ServiceStrategy;
import com.oversea.common.annotation.PostMethod;
import com.oversea.common.annotation.ServiceDefinition;
import com.oversea.common.constant.Constants;
import com.oversea.common.core.ServiceMethod;
import com.oversea.common.util.ClassUtil;
/**
 * spring初始化完成后,将扫描指定目录下的service类
 * @author fengjian
 *
 */
public class OverSeaServiceScanner implements ApplicationListener<ContextRefreshedEvent> {
    
    private static final Logger log = Logger.getLogger(OverSeaServiceScanner.class);
	
	//private static final String REQUEST_BASE_PARAMS_CLASS	= RequestBaseParams.class.getSimpleName();
	
	//private static final String RESPONSE_BASE_PARAMS_CLASS	= ResponseBaseParams.class.getSimpleName();

    private ConcurrentHashMap<String,ServiceMethod> methods = new ConcurrentHashMap<String,ServiceMethod>();
    
    private ServiceStrategy serviceStrategy; 
    
    public void setServiceStrategy(ServiceStrategy serviceStrategy) {
        this.serviceStrategy = serviceStrategy;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() != null){
            return;
        }
        //查找包里的service类
        List<Class<?>> serviceInterfaceList = ClassUtil.getInterface(Constants.SCAN_SERVICE_PACKAGE,true);
        
        for(Class<?> serviceInterface:serviceInterfaceList){
            List<Class<?>> classlist = ClassUtil.getAllClassByInterface(serviceInterface);
            if(classlist==null || classlist.size()==0){
                continue;
            }
            
            Class<?> serviceClass = classlist.get(0);
            
            ServiceDefinition anno = getServiceDefinition(serviceClass);
            if(anno==null){
                continue;
            }
            for(Method method: serviceClass.getMethods()){
                PostMethod methodType = method.getAnnotation(PostMethod.class);
                ServiceMethod _config = null;
                if(methodType != null){
                    _config = new ServiceMethod();
                    Object service = serviceStrategy.getInstance(serviceInterface);
                    //找不到远程方法则忽略
                    if(service==null){
                        continue;
                    }
                    _config.setObject(service);
                    //反射出实现类的方法
                    Method methodProxy = findMethod(service.getClass(),method.getName(),method.getParameterTypes());
                    if(methodProxy==null){
                        throw new RuntimeException(String.format("can't found class类%s method name %s,because service not export this method %s",service.getClass(), method.getName(),methodType.value()));
                    }
                    _config.setMethod(methodProxy);
                    _config.setNeedLogin(methodType.needLogin());
                    _config.setNeedCookie(methodType.needCookie());
                    methods.put(methodType.value(), _config);
                }
            }
        }
    }
    
    /**
     * 根据操作类型,返回对应的service实例及method信息
     * @param type
     * @return
     */
    public ServiceMethod getServiceByOperationType(String type){
        return methods.get(type);
    }

    /**
     * 获取OperationType注解信息
     * @param c
     * @return
     */
    public static List<PostMethod> getOperactionList(Class<?> c){
        List<com.oversea.common.annotation.PostMethod> reslut = new ArrayList<com.oversea.common.annotation.PostMethod>();
        Class<?> entity = (Class<?>)((ParameterizedType)c.getGenericSuperclass()).getActualTypeArguments()[0];  
        if(entity!=null){
            Method[] methods = entity.getMethods();  
            for(Method m:methods){  
                com.oversea.common.annotation.PostMethod operationType = m.getAnnotation(com.oversea.common.annotation.PostMethod.class);  
                if(operationType!=null){  
                    reslut.add(operationType);  
                }  
            } 
        }
        return reslut;
    }
    
    /**
     * 获取ServiceDefinition注解信息
     * @param c
     * @return
     */
    public static ServiceDefinition getServiceDefinition(Class<?> c){
        return  c.getAnnotation(ServiceDefinition.class);
    }
    
    private static Method findMethod(Class<?> clazz,String methodName,Class<?>[] arguments){
        try {
            return clazz.getMethod(methodName, arguments);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(),e);
        } catch (SecurityException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
