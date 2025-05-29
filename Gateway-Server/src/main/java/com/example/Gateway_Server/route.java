package com.example.Gateway_Server;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class route {
    @Bean
    public RouterFunction<ServerResponse> BillService(){
        return GatewayRouterFunctions.route("Bill-Service")
                .route(RequestPredicates.path("/item/*"), HandlerFunctions.http("http://localhost:8081"))
                .build();

    }
    @Bean
    public RouterFunction<ServerResponse> CategoryService(){
        return GatewayRouterFunctions.route("Category-Service")
                .route(RequestPredicates.path("/category/*"), HandlerFunctions.http("http://localhost:8082"))
                .build();

    }
}
