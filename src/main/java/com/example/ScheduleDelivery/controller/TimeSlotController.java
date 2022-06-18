package com.example.ScheduleDelivery.controller;

import com.example.ScheduleDelivery.dto.AddressDto;
import com.example.ScheduleDelivery.dto.TimeSlotDto;
import com.example.ScheduleDelivery.service.base.TimeSlotService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Log4j2
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @SneakyThrows
    @PostMapping("/timeslots-retrieve")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<TimeSlotDto>> retrieveTimeSlots(@RequestBody AddressDto addressDto) {

        return ResponseEntity.ok(timeSlotService.getTimeSlots(addressDto));
    }
}
