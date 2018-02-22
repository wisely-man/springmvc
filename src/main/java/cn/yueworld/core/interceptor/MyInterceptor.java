package cn.yueworld.core.interceptor;

import cn.yueworld.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiangyi on 2017/5/19.
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SayHelloService sayHelloService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("MyInterceptor---start");
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        Long endTime = System.currentTimeMillis();
        sayHelloService.sayHello2();
        System.out.println("Transaction time consuming:"+(endTime-startTime));
        System.out.println("MyInterceptor---end");
    }
}
