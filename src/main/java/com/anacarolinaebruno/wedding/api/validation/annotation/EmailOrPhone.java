package com.anacarolinaebruno.wedding.api.validation.annotation;

import com.anacarolinaebruno.wedding.api.validation.validator.EmailOrPhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailOrPhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailOrPhone {
    String message() default "O campo deve ser um email válido ou número de telefone válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}