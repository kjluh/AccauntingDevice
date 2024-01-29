package com.accauntingdevice.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PlantDTO {

    private String name;
    private String address;
    private LocalDate createDate;

}
