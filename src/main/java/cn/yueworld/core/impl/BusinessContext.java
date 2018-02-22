package cn.yueworld.core.impl;

import cn.yueworld.core.IBusinessContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangyi on 2017/3/14.
 */
public class BusinessContext implements IBusinessContext {

    public BusinessContext() {
    }

    public BusinessContext(Map map){
        this.bodyMap = map;
    }

    private Map<String, Object> bodyMap = new HashMap<>();

    @Override
    public Map getParamters() {
        return bodyMap;
    }

    @Override
    public Object getParam(String key) {
        return bodyMap.get(key);
    }

    @Override
    public void setParam(String key, Object o) {
        bodyMap.put(key, o);
    }
}
