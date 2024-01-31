package com.accauntingdevice.service;

import com.accauntingdevice.dto.DeviceDTO;
import com.accauntingdevice.dto.PlantDTO;
import com.accauntingdevice.dto.StatisticPlantAndDeviceDTO;
import com.accauntingdevice.entity.Device;
import com.accauntingdevice.entity.Plant;
import com.accauntingdevice.repository.DeviceRepository;
import com.accauntingdevice.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StatisticService {

    private final DeviceRepository deviceRepository;
    private final PlantRepository plantRepository;

    public StatisticService(DeviceRepository deviceRepository, PlantRepository plantRepository) {
        this.deviceRepository = deviceRepository;
        this.plantRepository = plantRepository;
    }

    /**
     * Метод вернет 5 Лучших заводов по количеству выпущенной продукции
     *
     * @return лист заводовДТО
     */
    public List<PlantDTO> plantCreateFor7Day() {
        List<Long> idPlantList = deviceRepository.findAllByDateCreate(LocalDate.now().minusDays(7));
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

    /**
     * Метод вернет 5 последних выпущенных устройств
     *
     * @return лист устройствДТО
     */
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

    /**
     * Метод получения завода и количества выпущенной продукции за 30 дней
     *
     * @return мап заводаДТО и количества продукции
     */
    public List<StatisticPlantAndDeviceDTO> statistic() {
        List<StatisticPlantAndDeviceDTO> statisticPlantAndDeviceDTOList = new ArrayList<>();
        List<Device> allByDateCreateAfter = deviceRepository.findAllByDateCreateAfter(LocalDate.now().minusDays(30))
                .stream().sorted(Comparator.comparing(e -> e.getPlant().getId())).toList();
        List<Long> plantIds = allByDateCreateAfter.stream().map(e -> e.getPlant().getId()).distinct().toList();
        for (int i = 0; i < plantIds.size(); i++) {
            statisticPlantAndDeviceDTOList.add(new StatisticPlantAndDeviceDTO(
                    mapperPlantDTO(plantRepository.findById(plantIds.get(i)).orElseThrow()),
                    countDevice(allByDateCreateAfter, plantIds.get(i))));
        }
        return statisticPlantAndDeviceDTOList.stream().sorted(Comparator.comparing(StatisticPlantAndDeviceDTO::getQualityDevice).reversed()).toList();
    }

    /**
     * Маппер для получения ДТО завода
     *
     * @param plant изначальный объект завод
     * @return мапленный объект завод ДТО
     */
    private PlantDTO mapperPlantDTO(Plant plant) {
        PlantDTO plantDTO = new PlantDTO();
        plantDTO.setName(plant.getName());
        plantDTO.setAddress(plant.getAddress());
        plantDTO.setCreateDate(plant.getCreateDate());
        return plantDTO;
    }

    /**
     * Метод считает количество продукции у завода
     * @param list лист продукции
     * @param idPlant id завода
     * @return количество изделий
     */
    private Long countDevice(List<Device> list, Long idPlant){
        return list.stream().filter(e->e.getPlant().getId()==idPlant).count();
    }
}
