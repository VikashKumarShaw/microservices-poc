package com.practice.demo.product.details.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoriesValidator implements ConstraintValidator<Categories, String>{

	List<String> categories = Arrays.asList("electronics","clothing","groceries");
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return categories.contains(value.toLowerCase());
	}

}
