package com.example.Order_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@Configuration
public class OrderServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderServiceApplication.class, args);
	}

	// ✅ Ajout du RestTemplate comme Bean pour l'injection
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
