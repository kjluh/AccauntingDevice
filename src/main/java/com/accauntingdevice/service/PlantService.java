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

    /**
     * Метод получения всего списка заводов
     * @return лист заводовДТО
     */
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

    /**
     * Метод сохранения завода в бд
     * @param plantDTO принимает ДТОзавода
     * @return ДТОзавода
     */
    public PlantDTO save(PlantDTO plantDTO) {
        Plant plant = new Plant();
        plant.setAddress(plantDTO.getAddress());
        plant.setName(plantDTO.getName());
        plant.setCreateDate(plantDTO.getCreateDate());
        plant.setDateAddInBase(LocalDate.now());
        plantRepository.save(plant);
        return plantDTO;
    }

    /**
     * Метод удаления завода из бд
     * @param id завода
     */
    public void delete(Long id) {
        plantRepository.deleteById(id);
    }

    /**
     * Метод поиска завода по id в бд
     * @param id id завода
     * @return завод из бд
     */
    public Plant find(Long id){
        return plantRepository.findById(id).orElseThrow();
    }
}
