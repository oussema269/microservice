package com.example.microservice.domaine.proxies;

import com.example.microservice.domaine.DTO.CourDTO;
import com.example.microservice.domaine.DomaineServiceimpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "MICROSERVICE")
public interface CourClient {
    @RequestMapping("/cour/ajouterCour")
    public CourDTO ajouterCour(@RequestBody CourDTO c) ;



    @RequestMapping("/cour/getCour")
    public List<CourDTO> getCour() ;

    @RequestMapping("/cour/getCourbyid")
    public CourDTO getCourByDomaine(@RequestParam("idCour") String idCour);
}
