package cn.yueworld.core.annotation.aop;

import java.lang.annotation.*;

/**
 * Created by jiangyi on 2017/5/8.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAspectjAnnotation {

    String operationType();

    String operationName();

}
