package cn.yueworld.core;

import java.util.Map;

/**
 * Created by jiangyi on 2017/3/14.
 */
public interface IBusinessContext{

    public Map getParamters();

    public Object getParam(String key);

    public void setParam(String key, Object object);

}
