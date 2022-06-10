package com.example.ScheduleDelivery.repository;

import com.example.ScheduleDelivery.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
}
