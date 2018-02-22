package cn.yueworld.common.tools;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jiangyi on 2017/5/26.
 */
public class MyConfiguration extends PropertyPlaceholderConfigurer{

    private static Map<String, String> propertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);

        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertiesMap.put(keyStr, value);
        }
    }

    public static String getProperty(String key){
        return propertiesMap.get(key);
    }
    public static String getProperty(String key, String defVal){
        return getProperty(key)==null ? defVal : getProperty(key);
    }
}
