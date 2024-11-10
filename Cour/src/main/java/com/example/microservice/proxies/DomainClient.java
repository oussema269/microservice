package com.example.microservice.proxies;

import com.example.microservice.DTO.Domain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "DOMAINE")
public interface DomainClient {
    @RequestMapping("/domaine/Domaines")
    public List<Domain>Listededomaine();
    @RequestMapping("/domaine/addDomaine")
    public Domain addDomaine(@RequestBody Domain d);


    }
