package cn.yueworld.service;

import cn.yueworld.core.annotation.aop.LogAspectjAnnotation;
import org.springframework.stereotype.Service;


/**
 * Created by jiangyi on 2017/4/24.
 */
@Service
public class SayHelloService {

    @LogAspectjAnnotation(operationType="say", operationName="sayHello")
    public String sayHello(String name){
        return "Hello " + name;
    }

    @LogAspectjAnnotation(operationType="say2", operationName = "sayHello2")
    public void sayHello2(){
        System.out.println("no obj return");
    }
}
