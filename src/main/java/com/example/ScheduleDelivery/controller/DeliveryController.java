package com.example.ScheduleDelivery.controller;

import com.example.ScheduleDelivery.service.base.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @SneakyThrows
    @PostMapping("/resolve-address")
    @ResponseStatus(HttpStatus.OK)
    public void resolveAddress(@RequestBody  String address) {

        deliveryService.resolveAddress(address);
    }
}
