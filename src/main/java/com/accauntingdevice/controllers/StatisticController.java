package com.accauntingdevice.controllers;

import com.accauntingdevice.dto.DeviceDTO;
import com.accauntingdevice.dto.PlantDTO;
import com.accauntingdevice.dto.StatisticPlantAndDeviceDTO;
import com.accauntingdevice.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytic")
public class StatisticController {

    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping("/7day")
    public List<PlantDTO> plantCreateFor7Day(){
        return service.plantCreateFor7Day();
    }

    @GetMapping("/5device")
    public List<DeviceDTO> last5Device(){
        return service.last5Device();
    }

    @GetMapping("/stats30d")
    public List<StatisticPlantAndDeviceDTO> stats(){
        return service.statistic();
    }
}
