package com.accauntingdevice.repository;

import com.accauntingdevice.entity.Device;
import org.hibernate.engine.spi.ExecutableList;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    List<Device> findAllByPlantId(Long id);
    List<Device> findAllByNameDirectorChangeContainingIgnoreCase(String nameDirectorChange);
    List<Device> findAllByNameDirectorChangeContainingIgnoreCaseAndPlantId(String nameDirectorChange,Long id);
    @Query("select d.PLANT_ID,count(d.plant_id) from device d where d.date_create > ?1 group by d.plant_id limit 5")
    List<Device> findAllByFor7Day(LocalDate localDate);

    @Query("select * from device d order by d.DATE_CREATE desc limit 5")
    List<Device> last5Device();

    @Query("select d.PLANT_ID,count(d.plant_id) from device d where d.date_create > ?1 group by d.plant_id limit 5")
    List<Device> stats(LocalDate localDate);
}
