package com.accauntingdevice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ui;
    private LocalDate dateCreate;
    private String deviceName;
    private String nameDirectorChange;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(ui, device.ui) && Objects.equals(dateCreate, device.dateCreate) && Objects.equals(deviceName, device.deviceName) && Objects.equals(nameDirectorChange, device.nameDirectorChange) && Objects.equals(plant, device.plant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ui, dateCreate, deviceName, nameDirectorChange, plant);
    }
}
