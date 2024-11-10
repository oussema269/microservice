package com.example.poleservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PoleService implements IPoleService {

    @Autowired
    private PoleRepository poleRepository;

    @Autowired
    private RestTemplate restTemplate; // RestTemplate for external service calls

    @Override
    public Pole addPole(Pole pole) {
        return poleRepository.save(pole);
    }

    @Override
    public List<Pole> getAllPoles() {
        return poleRepository.findAll();
    }

    @Override
    public Pole getPoleById(String codePole) {
        return poleRepository.findById(codePole).orElse(null);
    }

    @Override
    public Pole updatePole(Pole pole, String id) {
        pole.setCodePole(id);
        return poleRepository.save(pole);
    }

    @Override
    public void deletePole(String id) {
        poleRepository.deleteById(id);
    }

    public List<Object> getFacultesForPole(String poleId) {
        Optional<Pole> pole = poleRepository.findById(poleId);
        List<Object> facultes = new ArrayList<>();

        // If pole exists and has related faculte IDs, fetch details from Faculte service
        if (pole.isPresent() && pole.get().getFaculteIds() != null) {
            for (String faculteId : pole.get().getFaculteIds()) {
                Object faculte = restTemplate.getForObject("http://FACULTE-SERVICE/faculte/get/" + faculteId, Object.class);
                facultes.add(faculte);
            }
        }
        return facultes;
    }
}