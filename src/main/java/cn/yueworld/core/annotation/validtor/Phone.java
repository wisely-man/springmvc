package cn.yueworld.core.annotation.validtor;

import cn.yueworld.core.actions.validtor.PhoneValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by jiangyi on 2017/5/23.
 */
@Documented
@Constraint(validatedBy = PhoneValidtor.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

    String message() default "phone.error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
