package com.accauntingdevice.repository;

import com.accauntingdevice.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant,Long> {
}
