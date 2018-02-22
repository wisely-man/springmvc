package cn.yueworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jiangyi on 2017/5/19.
 */
@Component
public class InvokerClass {

    @Autowired
    List<Base> baseList;

    public void print(){
        for(Base base : baseList){
            base.print();
        }
    }
}
