package com.example.ScheduleDelivery.service.base;

import com.example.ScheduleDelivery.dto.AddressDto;
import com.example.ScheduleDelivery.dto.TimeSlotDto;

import java.util.Set;

public interface TimeSlotService {

    Set<TimeSlotDto> getTimeSlots(AddressDto addressDto );
}
