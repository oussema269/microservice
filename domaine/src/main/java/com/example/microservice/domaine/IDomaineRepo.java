package com.example.microservice.domaine;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomaineRepo extends MongoRepository<domaine,String> {
}
