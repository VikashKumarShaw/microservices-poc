package com.practice.demo.product.details.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
	public void updateProduct(@RequestBody ProductDetailsDTO newProductDetails,
			@PathVariable("name") String name) {
		
		Optional.ofNullable(productDetailsService.searchProduct(name)).filter(isName -> !isName).ifPresent(isName  -> {
			throw new ProductDoesNotExistException("Product with name " + name + " does not exist");
		});
//		if (!productDetailsService.searchProduct(name))
//			throw new ProductDoesNotExistException("Product with name " + name + " does not exist");
//		productDetailsService.getProduct(name).ifPresent(product -> {
//			product.setBrand(newProductDetails.getBrand());
//			product.setPrice(newProductDetails.getPrice());
//			product.setQuantity(newProductDetails.getQuantity());
//			product.setCategory(newProductDetails.getCategory());
//			productDetailsService.updateProduct(product);
//		});
		productDetailsService.getProduct(name).ifPresent(product -> {
			copyNonNullProperties(newProductDetails, product);
			productDetailsService.updateProduct(product);
		});
	}
	
	public static void copyNonNullProperties(Object src, Object target) {
	    BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
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
