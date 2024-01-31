package com.accauntingdevice.service;

import com.accauntingdevice.dto.DeviceDTO;
import com.accauntingdevice.entity.Device;
import com.accauntingdevice.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final PlantService plantService;

    public DeviceService(DeviceRepository deviceRepository, PlantService plantService) {
        this.deviceRepository = deviceRepository;
        this.plantService = plantService;
    }

    /**
     * Метод поиска устройств в базе по заводу и имени начальника смены
     * @param idPlant id завода
     * @param nameDirectorChange имя начальника смены
     * @return лист устройств
     */
    public List<DeviceDTO> getAll(Long idPlant, String nameDirectorChange) {
        if (null != idPlant && null != nameDirectorChange) {
            return mapper(deviceRepository.findAllByNameDirectorChangeContainingIgnoreCaseAndPlantId(nameDirectorChange,idPlant));
        }
        if (null != nameDirectorChange) {
            return mapper(deviceRepository.findAllByNameDirectorChangeContainingIgnoreCase(nameDirectorChange));
        }
        if (null != idPlant) {
            return mapper(deviceRepository.findAllByPlantId(idPlant));
        }
        return mapper(deviceRepository.findAll());
    }

    /**
     * Метод сохранения устройств в бд
     * @param deviceDTO ДТОустройства
     * @return ДТОустройства
     */
    public DeviceDTO save(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setDeviceName(deviceDTO.getDeviceName());
        device.setUi(deviceDTO.getUi());
        device.setPlant(plantService.find(deviceDTO.getPlantID()));
        device.setNameDirectorChange(deviceDTO.getNameDirectorChange());
        device.setDateCreate(deviceDTO.getDateCreate());
        deviceRepository.save(device);
        return deviceDTO;
    }

    /**
     * Метод удаления устройств в бд
     * @param id id устройства
     */
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }

    /**
     * Маппер устройств в ДТО
     * @param list лист устройств
     * @return лист ДТОустройств
     */
    private List<DeviceDTO> mapper(List<Device> list){
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        for (Device device : list) {
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
}
