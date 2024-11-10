package com.example.poleservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PoleRepository extends MongoRepository<Pole,String> {
    Pole findPoleByCodePole(String code);
}
