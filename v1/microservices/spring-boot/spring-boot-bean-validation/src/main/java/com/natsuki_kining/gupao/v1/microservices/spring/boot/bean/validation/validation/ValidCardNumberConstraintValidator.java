package com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.validation;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.validation.constraints.ValidCardNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ValidCardNumberConstraintValidator implements ConstraintValidator<ValidCardNumber,String> {

    @Override
    public void initialize(ValidCardNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        String[] parts = StringUtils.split(value,"-");
        if(ArrayUtils.getLength(parts) != 2){
            return false;
        }

        String prefix = parts[0];
        String suffix = parts[1];

        boolean isValidPrefix = Objects.equals(prefix, "GUPAO");

        boolean isValidInteger = StringUtils.isNumeric(suffix);

        return isValidPrefix & isValidInteger;
    }
}
