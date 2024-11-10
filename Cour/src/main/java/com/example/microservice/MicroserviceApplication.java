package com.example.microservice;

import com.example.microservice.entity.Cour;
import com.example.microservice.repository.ICourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);}
@Autowired
private ICourRepository repository;
	/*@Bean
	ApplicationRunner init() {
		return (args) -> {
			// save
			repository.save(new Cour("Java", "spring", 14));
			repository.save(new Cour("JavaScript", "node", 77));
			repository.save(new Cour("TypeScript", "angular", 88));
			repository.save(new Cour("C#", "dotnet", 63));
			// fetch
			repository.findAll().forEach(System.out::println);
		};
	}
*/
}
