package com.practice.demo.product.details.models;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailResponse {

	private HttpStatus status;
	private Object message;
	private Object details;
}
