package com.example.microservice.service;

import com.example.microservice.entity.Cour;
import com.example.microservice.entity.Niveau;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICourService {
    public Cour ajouterCour(Cour c);
    public Cour modifierCour(Cour c ,String idc);
    public void supprimerCour(String id);
    public List<Cour> getCour();
    List<Cour> findAllByOrderByDateDesc();
    List<Cour> findAllByNomCour(String  nom);
    public String storeFile(MultipartFile file, String blogCode);
    public Resource loadFileAsResource(String fileName);
    public Cour getCCourByid(String id);
    //public Cour affecterRessourcesACour(Ressource r , String idc);
    //public String storeFileRessource(MultipartFile file, String idRessource);
    List<Cour> findCoursByDateGreaterThan();
    List<Cour> filterByNiveau(Niveau niveau);
   public List<Cour> rechercheCour(String recherche);
   // public List<Cour> getCourByDomaine(String idDomaine);

}
