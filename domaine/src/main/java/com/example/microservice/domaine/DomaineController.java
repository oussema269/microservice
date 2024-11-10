package com.example.microservice.domaine;

import com.example.microservice.domaine.DTO.CourDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/domaine")
public class DomaineController {
    @Autowired
    IDomaineService iDomaineService;
    @Autowired
    IDomaineRepo domaineRepository;
    @Autowired
    DomaineServiceimpl domaineServiceimpl;
    @PostMapping("/addDomaine")
    public domaine addDomaine(@RequestBody domaine d){

        return iDomaineService.ajoutDomaine(d);
    }
    @GetMapping("/Domaines")

    public List<domaine> Listededomaine()
    {
        return iDomaineService.listededomaine();
    }
    @DeleteMapping("/Domaine/{id}")

    public domaine deleteDomaineById(@PathVariable("id")
                                     String id)
    {
        return iDomaineService.deleteDomaineById(id);
    }
    @CrossOrigin("*")
    @PutMapping("/Domaine/{id}")
    public domaine updateDomaine(@RequestBody domaine domaine,@PathVariable ("id") String id)
    {
        return iDomaineService.updateDomaine(domaine,id);
    }

    @GetMapping("dd/{id}")
    public domaine getDomaineById(@PathVariable String id) {
        return domaineRepository.findById(id).orElse(null);




    }
    @PostMapping("/ajouterCour")
    public CourDTO ajouterCour(@RequestBody CourDTO courDTO ) {
        return domaineServiceimpl.ajoutCour(courDTO);
    }
    @GetMapping("/getCour")
    public List<CourDTO> getCour() {
        return domaineServiceimpl.listecours();
    }
    @GetMapping("/getCoursByDomain/{id}")
    public List<CourDTO>getCoursByDomain(@PathVariable("id") String id){
        return domaineServiceimpl.getCoursByDomain(id);
    }
}
