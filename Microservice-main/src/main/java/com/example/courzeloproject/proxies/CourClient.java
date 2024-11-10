package com.example.courzeloproject.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "MICROSERVICE")
public interface CourClient {
}
