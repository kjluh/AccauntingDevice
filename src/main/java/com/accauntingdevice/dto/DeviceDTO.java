package com.accauntingdevice.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class DeviceDTO {

    private String ui;
    private LocalDate dateCreate;
    private String deviceName;
    private String nameDirectorChange;

    private Long plantID;

}
