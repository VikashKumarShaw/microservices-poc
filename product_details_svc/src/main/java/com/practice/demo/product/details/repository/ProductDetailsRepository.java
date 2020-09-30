package com.practice.demo.product.details.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.demo.product.details.models.ProductDetailsDTO;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsDTO, Long>{

	public boolean existsByName(String name);

	public Optional<ProductDetailsDTO> findByName(String name);
}
