package com.accauntingdevice.service;

import com.accauntingdevice.dto.DeviceDTO;
import com.accauntingdevice.dto.PlantDTO;
import com.accauntingdevice.entity.Device;
import com.accauntingdevice.entity.Plant;
import com.accauntingdevice.repository.DeviceRepository;
import com.accauntingdevice.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StatisticService {

    private final DeviceRepository deviceRepository;
    private final PlantRepository plantRepository;

    public StatisticService(DeviceRepository deviceRepository, PlantRepository plantRepository) {
        this.deviceRepository = deviceRepository;
        this.plantRepository = plantRepository;
    }

    public List<PlantDTO> plantCreateFor7Day() {
        List<Long> idPlantList = deviceRepository.findAllByDateCreateAfter(LocalDate.now().minusDays(7));
        List<PlantDTO> plantList = new ArrayList<>();
        for (Long aLong : idPlantList) {
            PlantDTO plantDTO = new PlantDTO();
            Plant plant = plantRepository.findById(aLong).orElseThrow();
            plantDTO.setCreateDate(plant.getCreateDate());
            plantDTO.setAddress(plant.getAddress());
            plantDTO.setName(plant.getName());
            plantList.add(plantDTO);
        }
        return plantList;
    }

    public List<DeviceDTO> last5Device() {
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        for (Device device : deviceRepository.last5Device()) {
            DeviceDTO newDevice = new DeviceDTO();
            newDevice.setDeviceName(device.getDeviceName());
            newDevice.setUi(device.getUi());
            newDevice.setPlantID(device.getPlant().getId());
            newDevice.setNameDirectorChange(device.getNameDirectorChange());
            newDevice.setDateCreate(device.getDateCreate());
            deviceDTOList.add(newDevice);
        }
        return deviceDTOList;
    }

    public List<Plant> stats(){
//      return deviceRepository.stats(LocalDate.now().minusDays(30));
        return null;
    }
}
