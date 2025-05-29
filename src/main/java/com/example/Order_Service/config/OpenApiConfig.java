package com.example.Order_Service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de gestion des commandes - PayStock")
                        .version("1.0.0")
                        .description("Documentation des endpoints REST du microservice Order-Service"));
    }
}
