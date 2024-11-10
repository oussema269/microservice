package com.example.poleservice;

import java.util.List;

public interface IPoleService {
    Pole addPole(Pole pole);
    List<Pole> getAllPoles();
    Pole getPoleById(String codePole);
    Pole updatePole(Pole pole, String id);
    void deletePole(String id);
}