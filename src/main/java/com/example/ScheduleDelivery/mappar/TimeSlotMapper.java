package com.example.ScheduleDelivery.mappar;

import com.example.ScheduleDelivery.dto.TimeSlotDto;
import com.example.ScheduleDelivery.model.TimeSlot;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class TimeSlotMapper {


    @Autowired
    public ModelMapper mapper;

    public TimeSlotDto timeSlotToTimeSlotDto(TimeSlot timeSlot ) {
        TimeSlotDto map = mapper.map(timeSlot, TimeSlotDto.class);
        return map;
    }

    public Set<TimeSlotDto> mapTimeSlotsToDtos(Set<TimeSlot> timeSlots){
        return timeSlots.stream().map(this::timeSlotToTimeSlotDto).collect(Collectors.toSet());
    }
}
