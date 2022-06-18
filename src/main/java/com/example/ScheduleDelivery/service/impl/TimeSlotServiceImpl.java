package com.example.ScheduleDelivery.service.impl;

import com.example.ScheduleDelivery.dto.AddressDto;
import com.example.ScheduleDelivery.dto.TimeSlotDto;
import com.example.ScheduleDelivery.mappar.AddressMapper;
import com.example.ScheduleDelivery.mappar.TimeSlotMapper;
import com.example.ScheduleDelivery.model.Address;
import com.example.ScheduleDelivery.model.TimeSlot;
import com.example.ScheduleDelivery.repository.TimeSlotRepository;
import com.example.ScheduleDelivery.service.base.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    TimeSlotMapper timeSlotMapper;

    @Autowired
    AddressMapper addressMapper;


    @Override
    public Set<TimeSlotDto> getTimeSlots(AddressDto addressDto) {
        Address address = addressMapper.addressDtoToAddress(addressDto);
        String city = address.getCity();
        Set<TimeSlot> timeSlots = timeSlotRepository.getAllByType(city, true);
        return timeSlotMapper.mapTimeSlotsToDtos(timeSlots);

    }
}
