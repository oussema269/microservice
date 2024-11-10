package com.example.poleservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pole")

public class PoleController {

    @Autowired
    private IPoleService poleService;



    @PostMapping("/add")
    public Pole addPole(@RequestBody Pole pole) {
        return poleService.addPole(pole);
    }

    @GetMapping("/getAll")
    public List<Pole> getAllPoles() {
        return poleService.getAllPoles();
    }

    @GetMapping("/get/{id}")
    public Pole getPoleById(@PathVariable String id) {
        return poleService.getPoleById(id);
    }

    @PutMapping("/update/{id}")
    public Pole updatePole(@RequestBody Pole pole, @PathVariable String id) {
        return poleService.updatePole(pole, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePole(@PathVariable String id) {
        poleService.deletePole(id);
    }


}