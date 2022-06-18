package com.example.ScheduleDelivery.service.base;

import com.example.ScheduleDelivery.dto.AddressDto;
import com.example.ScheduleDelivery.dto.DeliveryDto;

import java.util.Set;

public interface DeliveryService {

    void resolveAddress(String address);

    String bookDelivery(Long timeSlotId, AddressDto addressDto );

    String markAsComplete(Long deliverId);

    String cancel(Long deliveryId);

    Set<DeliveryDto> retrieveAllToday();

    Set<DeliveryDto> retrieveAllWeekly();

}

