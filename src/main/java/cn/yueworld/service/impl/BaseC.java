package cn.yueworld.service.impl;

import cn.yueworld.service.Base;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by jiangyi on 2017/5/19.
 */
@Component
@Order(1)
public class BaseC implements Base {

    @Override
    public void print() {
        System.out.println("C");
    }
}
