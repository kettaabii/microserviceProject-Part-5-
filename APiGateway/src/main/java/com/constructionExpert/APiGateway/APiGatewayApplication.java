package com.constructionExpert.APiGateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;

import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
@EnableWebFlux
public class APiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(APiGatewayApplication.class, args);
	}

	@Bean
	public DiscoveryClientRouteDefinitionLocator locator(ReactiveDiscoveryClient rd, DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rd, dlp);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/v3/api-docs").and().method(HttpMethod.GET).uri("lb://project-service"))
				.route(r -> r.path("/v3/api-docs").and().method(HttpMethod.GET).uri("lb://TacheService"))
				.route(r -> r.path("/v3/api-docs").and().method(HttpMethod.GET).uri("lb://ressource-service"))
//.route(r -> r.path("/v3/api-docs").and().method(HttpMethod.GET).uri("lb://USER-SERVICE"))
				.build();
	}
}
