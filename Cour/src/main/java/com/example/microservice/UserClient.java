package com.example.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user", url = "http://localhost:8081")
public interface UserClient {

}
