package com.example.ScheduleDelivery.service.impl;

import com.example.ScheduleDelivery.model.Address;
import com.example.ScheduleDelivery.model.TimeSlot;
import com.example.ScheduleDelivery.repository.AddressRepository;
import com.example.ScheduleDelivery.repository.TimeSlotRepository;
import com.example.ScheduleDelivery.service.base.BaseDeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class DeliveryServiceImpl extends BaseDeliveryService implements com.example.ScheduleDelivery.service.base.DeliveryService {

    public DeliveryServiceImpl(AddressRepository addressRepository, TimeSlotRepository timeSlotRepository) {
        super(addressRepository, timeSlotRepository);
    }

    //   String json = "{ \"country\" : \"israel\", \"city\" : \"telAviv\",\"street\" : \"hasomer 10\",\"postcode\" : \"1234\" }";
    @SneakyThrows
    public void resolveAddress(String address) {
        Address resolvedAddress = mapToAddress(address);
        addressRepository.save(resolvedAddress);
    }


    @Override
    public Set<TimeSlot> getTimeSlotsForAddress() {
        return null;
    }

    private Address mapToAddress(String address) {
        ObjectMapper objectMapper = new ObjectMapper();
        Address address1 = null;
        try {
            address1 = objectMapper.readValue(address, Address.class);
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        log.info(address1.toString());
        return address1;
    }
}
