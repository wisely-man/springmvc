package cn.yueworld.core.actions.aop;

import cn.yueworld.core.annotation.aop.LogAspectjAnnotation;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by jiangyi on 2017/5/8.
 */
@Aspect
@Component
public class LogAspectj {

    @Around("@annotation(cn.yueworld.core.annotation.aop.LogAspectjAnnotation)")
    public Object doLogAspectj(ProceedingJoinPoint joinPoint){
        Object result = null;

        Long startTime = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        System.out.println("方法的返回类型:"+signature.getReturnType());
        System.out.println(signature.getReturnType().isAssignableFrom(String.class));
        System.out.println(signature.getReturnType().isAssignableFrom(void.class));

        LogAspectjAnnotation annotation = signature.getMethod().getAnnotation(LogAspectjAnnotation.class);
        System.out.println("当前动作类型:"+annotation.operationType()+";动作名称:"+annotation.operationName());

        Object[] params = joinPoint.getArgs();
        System.out.println("请求参数:"+ArrayUtils.toString(params));
        try {
            result = joinPoint.proceed(params);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("返回结果:"+result);

        Long endTime = System.currentTimeMillis();
        System.out.println("操作耗时:"+(endTime-startTime));

        return result;
    }

}
