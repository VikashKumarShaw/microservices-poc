package com.practice.demo.product.details.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.practice.demo.product.details.models.ProductDetailsDTO;
import com.practice.demo.product.details.repository.ProductDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {
	
	private final ProductDetailsRepository productDetailsRepository;
	
	public long getProductCount() {
		return productDetailsRepository.count();
	}

	public List<ProductDetailsDTO> getAllProducts() {
		return productDetailsRepository.findAll();
	}

	public boolean searchProduct(String name) {
		return productDetailsRepository.existsByName(name);
	}

	public Optional<ProductDetailsDTO> getProduct(String name) {
		return productDetailsRepository.findByName(name);
	}

	public Optional<ProductDetailsDTO> addProduct(@Valid ProductDetailsDTO productDetails) {
		return Optional.ofNullable(productDetailsRepository.save(productDetails));
		
	}
	
	public Optional<ProductDetailsDTO> updateProduct(@Valid ProductDetailsDTO productDetails) {
		return Optional.ofNullable(productDetailsRepository.save(productDetails));
		
	}
	@Transactional
	public void deleteProduct(String name) {
		productDetailsRepository.deleteByName(name);
		
	}
	

}
