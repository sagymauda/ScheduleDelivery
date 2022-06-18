package com.example.ScheduleDelivery.controller;

import com.example.ScheduleDelivery.dto.AddressDto;
import com.example.ScheduleDelivery.dto.DeliveryDto;
import com.example.ScheduleDelivery.service.base.DeliveryService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Log4j2
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @SneakyThrows
    @PostMapping("/resolve-address")
    @ResponseStatus(HttpStatus.OK)
    public void resolveAddress(@RequestBody String address) {

        deliveryService.resolveAddress(address);
    }

    @PostMapping("/book-delivery/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> bookDelivery(@RequestBody AddressDto addressDto, @PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.bookDelivery(id, addressDto));

    }

    @PostMapping("/deliveries/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> markDeliveryAsComplete(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.markAsComplete(id));

    }

    @DeleteMapping("/deliveries/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> cancelDelivery(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.cancel(id));

    }

    @GetMapping("/deliveries/daily")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<DeliveryDto>> retrieveTodayDeliveries() {
        return ResponseEntity.ok(deliveryService.retrieveAllToday());

    }

    @GetMapping("/deliveries/weekly")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<DeliveryDto>> retrieveWeeklyDeliveries() {
        return  ResponseEntity.ok(deliveryService.retrieveAllWeekly());

    }
}


