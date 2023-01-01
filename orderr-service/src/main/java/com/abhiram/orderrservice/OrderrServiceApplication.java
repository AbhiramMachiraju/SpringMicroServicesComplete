package com.abhiram.orderrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderrServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderrServiceApplication.class, args);
	}

}
