package com.example.microservice.domaine;

import com.example.microservice.domaine.DTO.CourDTO;
import com.example.microservice.domaine.proxies.CourClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DomaineServiceimpl implements IDomaineService {
    static String idCour;
    @Autowired
    IDomaineRepo iDomaineRepo;
    @Autowired
    CourClient courClient;
    public CourDTO ajoutCour(@RequestBody CourDTO c) {
        return courClient.ajouterCour(c);
    }
    public List<CourDTO> listecours() {
        return courClient.getCour();
    }

    @Override
    public domaine ajoutDomaine(domaine d) {
        return iDomaineRepo.save(d);
    }

    @Override
    public List<domaine> listededomaine() {
        return iDomaineRepo.findAll();
    }

    @Override
    public domaine deleteDomaineById(String id) {
        iDomaineRepo.deleteById(id);

        return null;
    }

    @Override
    public domaine updateDomaine(domaine d, String id) {
        domaine domaine;
        domaine = iDomaineRepo.findById(id).get();
        domaine.setNom(d.getNom());
        domaine.setDescription(d.getDescription());


        return iDomaineRepo.save(domaine);
    }
    /*
    public List<CourDTO>getCoursByDomain(String idDomaine){
        List<CourDTO> courDTOList=new ArrayList<>();
        domaine d=iDomaineRepo.findById(idDomaine).get();
        List<String> listId =d.getList();
        log.info("liste "+d.getList().get(0));
        for(String id:listId){
log.info("entrer dans la boucle");
            courDTOList.add(courClient.getCourByDomaine(id))  ;
            log.info("1+++*******************"+courDTOList);
        }
       return courDTOList;



    }
    */
    public List<CourDTO> getCoursByDomain(String idDomaine) {
        List<CourDTO> courDTOList = new ArrayList<>();


        // Récupération du domaine par son ID
        domaine d = iDomaineRepo.findById(idDomaine)
                .orElseThrow(() -> new RuntimeException("Domaine non trouvé"));

        // Récupération de la liste des identifiants de cours dans le domaine
        List<String> listId = d.getList();
        log.info("Liste d'ID de cours: " + listId);

        // Boucle pour récupérer chaque cours par son identifiant et l'ajouter à la liste
        for (String id : listId) {
            idCour=id;
            log.info("Récupération du cours avec l'ID : " + id);
            CourDTO courDTO = courClient.getCourByDomaine(id); // Appel au service distant avec l'ID du cours
            courDTOList.add(courDTO);
            log.info("Cours ajouté : " + courDTO);
        }
        return courDTOList;
    }

}
