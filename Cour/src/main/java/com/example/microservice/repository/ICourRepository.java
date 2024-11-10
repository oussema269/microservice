package com.example.microservice.repository;


import com.example.microservice.entity.Cour;
import com.example.microservice.entity.Niveau;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ICourRepository extends MongoRepository<Cour,String> {
    public List<Cour> findByDateYearBetween (int d1 , int d2);
    List<Cour> findAllByOrderByDateDesc();
    List<Cour> findAllByNomCour(String  nom);
    List<Cour> findByDateGreaterThan(Date date);
    List<Cour> findCoursByNiveau(Niveau niveau);
    List<Cour> findByNomCourIgnoreCaseOrDescriptionIgnoreCase(String nomCour, String description);
   // List<Cour> findCoursByDomaine_Id(String idDomaine);
}
