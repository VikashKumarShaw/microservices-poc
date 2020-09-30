package com.practice.demo.product.details.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CategoriesValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Categories {
	String message() default "Categories must Electronics, Clothing or Groceries.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
