package com.practice.demo.product.details.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.product.details.exception.ProductDoesNotExistException;
import com.practice.demo.product.details.models.DetailResponse;
import com.practice.demo.product.details.service.ProductDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsController {

	private final ProductDetailsService productDetailsService;

	@GetMapping("/getAllProducts")
	public ResponseEntity<Object> getAllProducts() {
		if (productDetailsService.getProductCount() == 0)
			throw new ProductDoesNotExistException("No products are present in the catalog");

		DetailResponse response = new DetailResponse(HttpStatus.OK, "Products Found",
				productDetailsService.getAllProducts());
		log.info("Product Details :{}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getProduct/{name}")
	public ResponseEntity<Object> getProductByName(@PathVariable("name") String name) {
		if (!productDetailsService.searchProduct(name))
			throw new ProductDoesNotExistException("Product with name " + name + " does not exist");
		DetailResponse response = new DetailResponse(HttpStatus.OK, "Required Product found.",
				productDetailsService.getProduct(name));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
