package com.accauntingdevice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private LocalDate createDate;
    private LocalDate dateAddInBase;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(name, plant.name) && Objects.equals(address, plant.address) && Objects.equals(createDate, plant.createDate) && Objects.equals(dateAddInBase, plant.dateAddInBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, createDate, dateAddInBase);
    }
}
