package com.accauntingdevice.repository;

import com.accauntingdevice.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    List<Device> findAllByPlantId(Long id);
    List<Device> findAllByNameDirectorChangeContainingIgnoreCase(String nameDirectorChange);
    List<Device> findAllByNameDirectorChangeContainingIgnoreCaseAndPlantId(String nameDirectorChange,Long id);
}
