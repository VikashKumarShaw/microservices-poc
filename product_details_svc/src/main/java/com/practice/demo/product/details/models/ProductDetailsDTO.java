package com.practice.demo.product.details.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.practice.demo.product.details.validator.Categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductDetails")
public class ProductDetailsDTO implements Serializable{

	private static final long serialVersionUID = 8514895893317351518L;
	@Id
	@Column(nullable = false, length =  50)
	private String productName;
	
	@Column(nullable = false, length =  50)
	private String brand;
	
	@Column(nullable = false, length =  50)
	private BigDecimal price;
	
	@Column(nullable = false, length =  50)
	private int quantity;
	
	@NonNull
	@Categories
	@Column(nullable = false, length =  50)
	private String category;
	
	@CreatedDate
	@Column(nullable = false, length =  50)
	private Date createdOn;
}
