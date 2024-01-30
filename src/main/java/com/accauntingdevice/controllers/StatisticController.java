package com.accauntingdevice.controllers;

import com.accauntingdevice.entity.Device;
import com.accauntingdevice.entity.Plant;
import com.accauntingdevice.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/static")
public class StatisticController {

    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping("/1")
    public List<Plant> plantCreateFor7Day(){
        return service.plantCreateFor7Day();
    }

    @GetMapping("/2")
    public List<Device> last5Device(){
        return service.last5Device();
    }

    @GetMapping("/3")
    public List<Plant> stats(){
        return service.stats();
    }
}
