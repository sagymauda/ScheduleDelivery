package com.example.ScheduleDelivery.repository;

import com.example.ScheduleDelivery.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {

    @Query(value = "SELECT * FROM timeslots WHERE supported_City = :supportedCity and is_Available =:isAvailable",nativeQuery = true)
    Set<TimeSlot> getAllByType(@Param("supportedCity") String supportedCity,@Param("isAvailable")Boolean isAvailable);
}
