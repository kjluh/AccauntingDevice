package com.accauntingdevice.controllers;

import com.accauntingdevice.dto.PlantDTO;
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
    public List<PlantDTO> getAll(){
        return plantService.getAll();
    }

    @PostMapping
    public PlantDTO save(@RequestBody PlantDTO plantDTO){
        return plantService.save(plantDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        plantService.delete(id);
    }
}
