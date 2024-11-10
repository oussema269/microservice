package com.example.blogamine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogAmineApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAmineApplication.class, args);
    }

}
