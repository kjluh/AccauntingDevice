package com.accauntingdevice.service;

import com.accauntingdevice.entity.Device;
import com.accauntingdevice.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAll(Long idPlant, String nameDirectorChange) {
        if (null != idPlant && null != nameDirectorChange) {
            return deviceRepository.findAllByNameDirectorChangeContainingIgnoreCaseAndPlantId(nameDirectorChange,idPlant);
        }
        if (null == idPlant) {
            return deviceRepository.findAllByNameDirectorChangeContainingIgnoreCase(nameDirectorChange);
        }
        if (null == nameDirectorChange) {
            return deviceRepository.findAllByPlantId(idPlant);
        }
        return deviceRepository.findAll();
    }

    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }
}
