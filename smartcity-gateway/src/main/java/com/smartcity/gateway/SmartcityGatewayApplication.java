package com.smartcity.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux // important pour WebClient
public class SmartcityGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartcityGatewayApplication.class, args);
    }
}
