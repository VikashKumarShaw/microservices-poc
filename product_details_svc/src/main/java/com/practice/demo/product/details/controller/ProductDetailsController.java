package com.practice.demo.product.details.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.product.details.exception.ProductDoesNotExistException;
import com.practice.demo.product.details.models.DetailResponse;
import com.practice.demo.product.details.models.ProductDetailsDTO;
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

	@PostMapping("/addProduct")
	public void addProduct(@Valid @RequestBody ProductDetailsDTO productDetails) {
		productDetailsService.getProduct(productDetails.getName()).ifPresentOrElse(product -> {
			int totalQuantity = product.getQuantity() + productDetails.getQuantity();
			product.setQuantity(totalQuantity);
			productDetailsService.updateProduct(product);
		}, () -> {
			productDetailsService.addProduct(productDetails);
		});
	}

	@PutMapping("/product/{name}")
	public void updateProduct(@Valid @RequestBody ProductDetailsDTO newProductDetails,
			@PathVariable("name") String name) {

		if (!productDetailsService.searchProduct(name))
			throw new ProductDoesNotExistException("Product with name " + name + " does not exist");
		productDetailsService.getProduct(name).ifPresent(product -> {
			product.setBrand(newProductDetails.getBrand());
			product.setPrice(newProductDetails.getPrice());
			product.setQuantity(newProductDetails.getQuantity());
			product.setCategory(newProductDetails.getCategory());
			productDetailsService.updateProduct(product);
		});
	}
	
	@Transactional
	@DeleteMapping("/product/{name}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("name") String name) {
		
		if (!productDetailsService.searchProduct(name))
			throw new ProductDoesNotExistException("Product with name " + name + " does not exist");
		productDetailsService.deleteProduct(name);
		DetailResponse response = new DetailResponse(HttpStatus.OK, "Required Product Deleted.",
				null);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
