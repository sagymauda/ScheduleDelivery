package com.example.ScheduleDelivery.repository;

import com.example.ScheduleDelivery.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
