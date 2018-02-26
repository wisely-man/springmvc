package cn.yueworld.config;

import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.swing.*;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by jiangyi on 2017/4/21.
 */
public class SpringApp {

    private SpringApp(){
    }

    private AnnotationConfigWebApplicationContext context;

    public void setContext(AnnotationConfigWebApplicationContext context) {
        this.context = context;
    }

    private static SpringApp instance = new SpringApp();

    public static SpringApp getInstance(){
        return instance;
    }

    public <T> T getBean(String beanName){
        return (T) context.getBean(beanName);
    }

    public <T> T getBean(String beanName, Class<T> clz){
        return context.getBean(beanName, clz);
    }

    /**
     * 根据接口类型获取所有实例 beanName
     * @param interfaceClz
     * @return
     */
    public String[] getBeanNamesByInterfaceType(Class interfaceClz){
        return this.context.getBeanNamesForType(interfaceClz);
    }

    /**
     * 根据接口类型获取所有实例 beanName
     * @param interfaceClz
     * @return
     */
    public <T> Map<String, T> getObjectByInterfaceType(Class<T> interfaceClz){
        return this.context.getBeansOfType(interfaceClz);
    }


    /**
     * 根据注解类型获取所有实例 beanName
     * @param annotationClz
     * @return
     */
    public String[] getBeanNamesByAnnotationType(Class annotationClz){
        return this.context.getBeanNamesForAnnotation(annotationClz);
    }

    /**
     * 根据注解类型获取所有实例
     * @param annotationClz
     * @return
     */
    public <T> Map<String, T> getObjectByAnnotationType(Class<Annotation> annotationClz){
        return (Map<String, T>) this.context.getBeansWithAnnotation(annotationClz);
    }

    /**
     *
     * @param location
     * @return
     */
    public Resource getResource(String location){
        if(this.context == null){
            return null;
        }

        return this.context.getResource(location);
    }
}
