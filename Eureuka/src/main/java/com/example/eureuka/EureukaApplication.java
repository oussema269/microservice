package com.example.eureuka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EureukaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EureukaApplication.class, args);
    }

}
