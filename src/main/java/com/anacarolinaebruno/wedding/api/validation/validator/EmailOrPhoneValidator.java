package com.anacarolinaebruno.wedding.api.validation.validator;

import com.anacarolinaebruno.wedding.api.validation.annotation.EmailOrPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailOrPhoneValidator implements ConstraintValidator<EmailOrPhone, String> {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return Pattern.matches(EMAIL_REGEX, value) || Pattern.matches(PHONE_REGEX, value);
    }
}