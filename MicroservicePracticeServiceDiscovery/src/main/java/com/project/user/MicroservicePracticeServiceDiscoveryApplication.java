package com.project.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicePracticeServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePracticeServiceDiscoveryApplication.class, args);
	}

}
