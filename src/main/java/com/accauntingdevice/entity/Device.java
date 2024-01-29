package com.accauntingdevice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue()
    private Long id;
    private LocalDate localDate;
    private String deviceName;
    private String nameDirectorChange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant.id")
    private Plant plant;
}
