package com.accauntingdevice.repository;

import com.accauntingdevice.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    List<Device> findAllByPlantId(Long id);
    List<Device> findAllByNameDirectorChangeContainingIgnoreCase(String nameDirectorChange);
    List<Device> findAllByNameDirectorChangeContainingIgnoreCaseAndPlantId(String nameDirectorChange,Long id);

    @Query(value = "select d2.plant_id,count(d2.plant_id) from device d2 where d2.date_create > ?1 group by d2.plant_id having count(d2.plant_id)>1 order by count(d2.plant_id) desc ",nativeQuery = true)

    List<Long> findAllByDateCreate(LocalDate localDate);

    @Query(value = "select * from device order by date_create desc limit 5",nativeQuery = true)
    List<Device> last5Device();


    List<Device> findAllByDateCreateAfter(LocalDate localDate);
}
