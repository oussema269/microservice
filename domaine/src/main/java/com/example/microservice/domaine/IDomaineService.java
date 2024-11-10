package com.example.microservice.domaine;

import java.util.List;

public interface IDomaineService {
    public domaine ajoutDomaine(domaine d);

    List<domaine> listededomaine ();
    public domaine deleteDomaineById(String id);
    public domaine updateDomaine(domaine d,String id);
}
