package cn.yueworld.core.actions.validtor;

import cn.yueworld.core.annotation.validtor.Phone;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by jiangyi on 2017/5/23.
 */
public class PhoneValidtor implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String phoneStr, ConstraintValidatorContext constraintValidatorContext) {

        if(StringUtils.isNotBlank(phoneStr) && // 不为空且不符合手机号规则
                !phoneStr.matches("^1[0-9]{10}$")){
            return false;
        }
        return true;
    }
}
