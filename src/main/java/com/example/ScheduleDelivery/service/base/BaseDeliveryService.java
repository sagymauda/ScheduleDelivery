package com.example.ScheduleDelivery.service.base;

import com.example.ScheduleDelivery.repository.AddressRepository;
import com.example.ScheduleDelivery.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseDeliveryService {

protected final AddressRepository addressRepository;
protected final TimeSlotRepository timeSlotRepository;


}
