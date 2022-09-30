package com.wrightapps.smartedu.feeManagementservice.validator.impl;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wrightapps.smartedu.feeManagementservice.validator.UUIDValidator;



public class UUIDFieldValidator implements ConstraintValidator<UUIDValidator, String> {
    private final String regex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}"; // the regex

    @Override
    public void initialize(UUIDValidator validUuid) {
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext cxt) {
        return uuid == null || uuid.matches(this.regex);
    }
}
