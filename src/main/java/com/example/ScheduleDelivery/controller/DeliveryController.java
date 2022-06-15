package com.example.ScheduleDelivery.controller;

import com.example.ScheduleDelivery.model.Address;
import com.example.ScheduleDelivery.model.Delivery;
import com.example.ScheduleDelivery.model.TimeSlot;
import com.example.ScheduleDelivery.service.base.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@Log4j2
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @SneakyThrows
    @PostMapping("/resolve-address")
    @ResponseStatus(HttpStatus.OK)
    public void resolveAddress(@RequestBody String address) {

        deliveryService.resolveAddress(address);
    }

    @SneakyThrows
    @PostMapping("/timeslots-retrieve")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<TimeSlot>> retrieveTimeSlots(@RequestBody Address address) {

        return ResponseEntity.ok(deliveryService.getTimeSlotsForAddress(address));
    }

    @PostMapping("/book-delivery/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> bookDelivery(@RequestBody Address address, @PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.bookDelivery(id, address));

    }

    @PostMapping("/deliveries/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> markDeliveryAsComplete(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.markAsCompleteDelivery(id));

    }

    @DeleteMapping("/deliveries/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String>  cancelDelivery(@PathVariable Long id) {
        return ResponseEntity.ok( deliveryService.cancelDelivery(id));

    }

    @GetMapping("/deliveries/daily")
    @ResponseStatus(HttpStatus.OK)
    public List<Delivery> retrieveTodayDeliveries() {
        return deliveryService.retrieveAllTodayDeliveries();

    }

    @GetMapping("/deliveries/weekly")
    @ResponseStatus(HttpStatus.OK)
    public List<Delivery> retrieveWeeklyDeliveries() {
        return deliveryService.retrieveWeeklyDeliveries();

    }
}


