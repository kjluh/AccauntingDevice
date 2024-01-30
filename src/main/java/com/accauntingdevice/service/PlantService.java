package com.accauntingdevice.service;

import com.accauntingdevice.dto.PlantDTO;
import com.accauntingdevice.entity.Plant;
import com.accauntingdevice.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<PlantDTO> getAll() {
        List<PlantDTO> dtoList = new ArrayList<>();
        for (Plant plant : plantRepository.findAll()) {
            PlantDTO plantDTO = new PlantDTO();
            plantDTO.setAddress(plant.getAddress());
            plantDTO.setName(plant.getName());
            plantDTO.setCreateDate(plant.getCreateDate());
            dtoList.add(plantDTO);
        }
        return dtoList;
    }

    public PlantDTO save(PlantDTO plantDTO) {
        Plant plant = new Plant();
        plant.setAddress(plantDTO.getAddress());
        plant.setName(plantDTO.getName());
        plant.setCreateDate(plantDTO.getCreateDate());
        plant.setDateAddInBase(LocalDate.now());
        plantRepository.save(plant);
        return plantDTO;
    }

    public void delete(Long id) {
        plantRepository.deleteById(id);
    }

    public Plant find(Long id){
        return plantRepository.findById(id).orElseThrow();
    }
}
