package com.wrightapps.smartedu.feeManagementservice.validator;


import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wrightapps.smartedu.feeManagementservice.validator.impl.UUIDFieldValidator;


@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UUIDFieldValidator.class)
@Documented
public @interface UUIDValidator {

    String message() default "Field must be a valid UUID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
