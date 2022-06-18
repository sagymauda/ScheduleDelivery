package com.example.ScheduleDelivery.service.impl;

import com.example.ScheduleDelivery.dto.AddressDto;
import com.example.ScheduleDelivery.dto.DeliveryDto;
import com.example.ScheduleDelivery.mappar.AddressMapper;
import com.example.ScheduleDelivery.mappar.DeliveryMapper;
import com.example.ScheduleDelivery.model.Address;
import com.example.ScheduleDelivery.model.Delivery;
import com.example.ScheduleDelivery.model.Status;
import com.example.ScheduleDelivery.model.TimeSlot;
import com.example.ScheduleDelivery.repository.AddressRepository;
import com.example.ScheduleDelivery.repository.DeliveryRepository;
import com.example.ScheduleDelivery.repository.TimeSlotRepository;
import com.example.ScheduleDelivery.service.base.DeliveryService;
import com.example.ScheduleDelivery.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    DeliveryMapper deliveryMapper;


    @Transactional
    public void resolveAddress(String address) {
        Address resolvedAddress = mapToAddress(address);
        addressRepository.save(resolvedAddress);
    }


    @Override
    @Transactional
    public String bookDelivery(Long timeSlotId, AddressDto addressDto) {
        Address address = addressMapper.addressDtoToAddress(addressDto);
        Optional<TimeSlot> timeSlotToBook = timeSlotRepository.findById(timeSlotId);

        if (timeSlotToBook.isEmpty())
            return "No Such TimeSlot";

        //i need to check if the time slot can have more than  2 deliveries and is available
        if (!Utils.validAvailabilityAndCapacityInTimeSlot(timeSlotToBook.get())) {
            return "TimeSlot is not available";
        }

        //i need to check if the addresses  set for the same city  of the user matching the timeSlot
        // i go inside in case it's the same city now i need to create a delivery for it
        if (Utils.checkIfCityMatch(timeSlotToBook.get().getSupportedCity(), address.getCity())) {

            //before i create the delivery i need to add 1 to timeSlot capacity;
            timeSlotToBook.get().setCapacity(timeSlotToBook.get().getCapacity() + 1);

            //in case i have 2 Deliveries already then i update the Availability to false
            if (timeSlotToBook.get().getCapacity().equals(2)) {
                timeSlotToBook.get().setIsAvailable(false);
            }
            //creating new DeliveryObject in the System
            deliveryRepository.save(createDelivery(address, timeSlotToBook.get()));
        }
        return "Delivery was Created";
    }

    @Transactional
    @Override
    public String markAsComplete(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);

        if (delivery.isEmpty())
            return "No Such Delivery";

        delivery.get().setStatus(Status.COMPLETED);
        deliveryRepository.save(delivery.get());

        return "Marked as complete";
    }

    @Override
    @Transactional
    public String cancel(Long deliveryId) {

        //i need to remove it from the Db
        Optional<Delivery> deliveryToCancel = deliveryRepository.findById(deliveryId);
        if (deliveryToCancel.isEmpty())
            return "No Such TimeSlot";


        //here i change the capacity because we cancel the delivery as well if there were 2 deliveries already i mae it isAvailable to true;
        TimeSlot timeSlotAfterDeliveryCancel = Utils.makeTimeSlotAvailableAgain(deliveryToCancel.get().getTimeSlot());

        timeSlotRepository.save(timeSlotAfterDeliveryCancel);
        // i need to change back the timeSlot because now he is  Available

        deliveryRepository.delete(deliveryToCancel.get());

        return "delivery was canceled";

    }

    @Override
    public Set<DeliveryDto> retrieveAllToday() {
        Set<Delivery> deliveries = deliveryRepository.findTodayDeliveries();
        return deliveryMapper.mapDeliveryToDeliveryDto(deliveries);

    }

    @Override
    public Set<DeliveryDto> retrieveAllWeekly() {
        Set<Delivery> deliveries = deliveryRepository.findThisWeekDeliveries();
        return deliveryMapper.mapDeliveryToDeliveryDto(deliveries);

    }

    @SneakyThrows
    private Address mapToAddress(String address) {
        ObjectMapper objectMapper = new ObjectMapper();
        Address address1;
        address1 = objectMapper.readValue(address, Address.class);
        return address1;
    }


    private Delivery createDelivery(Address address, TimeSlot timeSlot) {
        Delivery delivery = new Delivery();
        delivery.setSelectedAddress(address);
        delivery.setTimeSlot(timeSlot);
        delivery.setStatus(Status.AWAITING);
        return delivery;
    }

}
