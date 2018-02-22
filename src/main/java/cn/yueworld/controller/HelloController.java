package cn.yueworld.controller;

import cn.yueworld.common.utils.ResponseBuilder;
import cn.yueworld.config.SpringApp;
import cn.yueworld.core.IBusinessContext;
import cn.yueworld.entity.Student;
import cn.yueworld.entity.User;
import cn.yueworld.service.InvokerClass;
import cn.yueworld.service.SayHelloService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangyi on 2017/4/18.
 */
@RequestMapping("/hello")
@Controller
public class HelloController {

    /**
     * 自定义HttpMessageConverter测试
     * @param ctx
     * @return
     */
    @RequestMapping(value="/businessCtxTest", produces = { "application/wisely" })
    @ResponseBody
    public Map businessCtxTest(@RequestBody IBusinessContext ctx){

        System.out.println(ctx.getParamters());
        ctx.setParam("A", "1");
        return ctx.getParamters();
    }


    /**
     * 表单验证测试--注解
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping("/validTestByAnnotation")
    @ResponseBody
    public Map validTestByAnnotation(@Valid User user, BindingResult bindingResult){

        // 表单验证
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            return ResponseBuilder.buildError(errMsg);
        }

        // 获取Spring容器中注册的Bean
        SayHelloService service =
                SpringApp.getInstance().getBean("sayHelloService", SayHelloService.class);
        String resultStr = service.sayHello(user.getName());
        service.sayHello2();

        return ResponseBuilder.buildSuccess(resultStr);
    }

    /**
     * 表单验证测试
     * @param bindingResult
     * @return
     */
    @RequestMapping("/validTestByBeanValid")
    @ResponseBody
    public Map validTestByBeanValid(@Valid Student student, BindingResult bindingResult){

        // 表单验证
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            return ResponseBuilder.buildError(errMsg);
        }

        return ResponseBuilder.buildSuccess();
    }




    /**
     * baseListPrint
     * @return
     */
    @RequestMapping("/baseListPrint")
    @ResponseBody
    public Map baseListPrint(){

        InvokerClass invoker = SpringApp.getInstance().getBean("invokerClass");
        invoker.print();

        return ResponseBuilder.buildSuccess();
    }

}
