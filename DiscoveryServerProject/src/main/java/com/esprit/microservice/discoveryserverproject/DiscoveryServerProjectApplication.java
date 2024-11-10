package com.esprit.microservice.discoveryserverproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerProjectApplication.class, args);
	}

}
