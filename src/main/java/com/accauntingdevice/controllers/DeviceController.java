package com.accauntingdevice.controllers;

import com.accauntingdevice.entity.Device;

import com.accauntingdevice.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/{idPlant}")
    public List<Device> getAllPlants(@PathVariable(required = false) Long idPlant,
                                     @RequestParam(required = false) String nameDirectorChange){
        return deviceService.getAll(idPlant,nameDirectorChange);
    }

    @PostMapping
    public Device saveDevice(@RequestBody Device device){
        return deviceService.save(device);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id){
        deviceService.delete(id);
    }
}
