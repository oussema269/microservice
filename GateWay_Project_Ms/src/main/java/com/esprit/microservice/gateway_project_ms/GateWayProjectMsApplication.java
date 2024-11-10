package com.esprit.microservice.gateway_project_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayProjectMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayProjectMsApplication.class, args);
	}
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder ){
		return builder.routes()
               .route("UserManagement_Ms", r->r.path("/api/**")
                        .uri("http://usermanagement:8282/"))
				.build() ;
	}
}
