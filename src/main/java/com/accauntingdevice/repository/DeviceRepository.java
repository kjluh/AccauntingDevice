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

//    @Query(value = "select d.PLANT_ID,count(d.plant_id) from device d where d.date_create > ?1 group by d.plant_id limit 5",nativeQuery = true)
    @Query(value = "select distinct d.plant_id from device d join (select d2.PLANT_ID,count(d2.plant_id) from device d2 where d2.date_create > ?1 group by d2.plant_id order by count(d2.plant_id) desc limit 5) d1 where d.plant_id = d1.PLANT_ID",nativeQuery = true)
    List<Long> findAllByDateCreateAfter(LocalDate localDate);

    @Query(value = "select * from device order by date_create desc limit 5",nativeQuery = true)
    List<Device> last5Device();

//    @Query("select d.PLANT_ID,count(d.plant_id) from device d where d.date_create > ?1 group by d.plant_id limit 5")
//    List<Device> stats(LocalDate localDate);
}
