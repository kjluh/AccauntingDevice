package com.accauntingdevice.service;

import com.accauntingdevice.entity.Plant;
import com.accauntingdevice.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAll() {
        return plantRepository.findAll();
    }

    public Plant save(Plant plant) {
        return plantRepository.save(plant);
    }

    public void delete(Long id) {
        plantRepository.deleteById(id);
    }
}
