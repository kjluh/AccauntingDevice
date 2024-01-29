package com.accauntingdevice.controllers;

import com.accauntingdevice.entity.Plant;
import com.accauntingdevice.service.PlantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plant")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public List<Plant> getAll(){
        return plantService.getAll();
    }

    @PostMapping
    public Plant save(@RequestBody Plant plant){
        return plantService.save(plant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        plantService.delete(id);
    }
}