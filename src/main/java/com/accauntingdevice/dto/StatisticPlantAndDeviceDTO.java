package com.accauntingdevice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticPlantAndDeviceDTO {

    private PlantDTO plantDTO;
    private Long qualityDevice;
}
