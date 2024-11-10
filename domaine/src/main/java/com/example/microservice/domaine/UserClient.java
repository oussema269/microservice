package com.example.microservice.domaine;

import com.example.microservice.domaine.DTO.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "UserClient-s", url = "http://localhost:8081")
public interface UserClient {
    @RequestMapping("users")
    public List<UserDto> getAllJobs();

    @RequestMapping("users/{id}")
    public UserDto getUserById(@PathVariable int id);
}

