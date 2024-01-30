package com.accauntingdevice.service;

import com.accauntingdevice.entity.Device;
import com.accauntingdevice.entity.Plant;
import com.accauntingdevice.repository.DeviceRepository;
import com.accauntingdevice.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {

    private final DeviceRepository deviceRepository;
    private final PlantRepository plantRepository;

    public StatisticService(DeviceRepository deviceRepository, PlantRepository plantRepository) {
        this.deviceRepository = deviceRepository;
        this.plantRepository = plantRepository;
    }

    public List<Plant> plantCreateFor7Day() {
        List<Device> deviceList = deviceRepository.findAllByFor7Day(LocalDate.now().minusDays(7));
        List<Plant> plantList = new ArrayList<>();
        for (Device device : deviceList) {
            plantList.add(plantRepository.findById(device.getPlant().getId()).orElseThrow());
        }
        return plantList;
    }

    public List<Device> last5Device() {
        return deviceRepository.last5Device();
    }

    public List<Plant> stats(){
        deviceRepository.stats(LocalDate.now().minusDays(30));
        return null;
    }
}
