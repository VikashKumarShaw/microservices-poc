package com.practice.demo.product.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductDetailsSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDetailsSvcApplication.class, args);
	}

}
