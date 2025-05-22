package com.example.Bill_Service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de gestion des factures")
                        .version("1.0.0")
                        .description("Documentation de l’API REST pour le microservice de gestion des factures.")
                        .contact(new Contact()
                                .name("Ton Nom")
                                .email("ton.email@example.com")
                                .url("https://ton-site.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}