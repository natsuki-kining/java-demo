package com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.validation.constraints;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.validation.ValidCardNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 合法卡号校验
 * 已gupao开头数字结尾
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ValidCardNumberConstraintValidator.class})
public @interface ValidCardNumber {

    String message() default "{com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.invalid.card.number.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
