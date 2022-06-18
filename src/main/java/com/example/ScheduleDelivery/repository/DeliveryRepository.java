package com.example.ScheduleDelivery.repository;

import com.example.ScheduleDelivery.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {


    @Query(value = "SELECT d.* FROM delivery as d JOIN timeslots AS t ON d.timeslots_id =t.id WHERE FORMATDATETIME(t.start_time,'yyyy-MM-dd')  = CURRENT_DATE", nativeQuery = true)
    Set<Delivery> findTodayDeliveries();

    @Query(value = "SELECT d.* FROM delivery as d JOIN timeslots AS t ON d.timeslots_id =t.id WHERE WEEK(FORMATDATETIME(t.start_time,'yyyy-MM-dd')) = WEEK(NOW())", nativeQuery = true)
    Set<Delivery> findThisWeekDeliveries();
}
