package com.practice.demo.product.details.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductDoesNotExistException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 628803451397948747L;

	public ProductDoesNotExistException(String exception) {
		super(exception);
	}

}
