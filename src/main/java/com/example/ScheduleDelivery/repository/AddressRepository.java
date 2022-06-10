package com.example.ScheduleDelivery.repository;

import com.example.ScheduleDelivery.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
