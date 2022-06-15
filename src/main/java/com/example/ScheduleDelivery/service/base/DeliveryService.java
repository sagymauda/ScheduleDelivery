package com.example.ScheduleDelivery.service.base;

import com.example.ScheduleDelivery.model.Address;
import com.example.ScheduleDelivery.model.Delivery;
import com.example.ScheduleDelivery.model.TimeSlot;

import java.util.List;
import java.util.Set;

public interface DeliveryService {

    void resolveAddress(String address);

    Set<TimeSlot> getTimeSlotsForAddress(Address address);

    String bookDelivery(Long timeSlotId, Address address);

    String markAsCompleteDelivery(Long deliverId);

    String cancelDelivery(Long deliveryId);

    List<Delivery> retrieveAllTodayDeliveries();

    List<Delivery> retrieveWeeklyDeliveries();

}
