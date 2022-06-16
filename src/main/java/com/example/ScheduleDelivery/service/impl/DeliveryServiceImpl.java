package com.example.ScheduleDelivery.service.impl;

import com.example.ScheduleDelivery.model.*;
import com.example.ScheduleDelivery.repository.AddressRepository;
import com.example.ScheduleDelivery.repository.DeliveryRepository;
import com.example.ScheduleDelivery.repository.TimeSlotRepository;
import com.example.ScheduleDelivery.service.base.BaseDeliveryService;
import com.example.ScheduleDelivery.service.base.DeliveryService;
import com.example.ScheduleDelivery.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeliveryServiceImpl extends BaseDeliveryService implements DeliveryService {

    public DeliveryServiceImpl(AddressRepository addressRepository, TimeSlotRepository timeSlotRepository, DeliveryRepository deliveryRepository) {
        super(addressRepository, timeSlotRepository, deliveryRepository);
    }

    @Transactional
    public void resolveAddress(String address) {
        Address resolvedAddress = mapToAddress(address);
        addressRepository.save(resolvedAddress);
    }

    @Override
    public Set<TimeSlot> getTimeSlotsForAddress(Address address) {
        String city = address.getCity();
        return timeSlotRepository.getAllByType(city, true);
    }

    @Override
    @Transactional
    public String bookDelivery(Long timeSlotId, Address address) {

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
    public String markAsCompleteDelivery(Long deliveryId) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);

        if (delivery.isEmpty())
            return "No Such Delivery";

        delivery.get().setStatus(Status.COMPLETED);
        deliveryRepository.save(delivery.get());

        return "Marked as complete";
    }

    @Override
    @Transactional
    public String cancelDelivery(Long deliveryId) {

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
    public List<Delivery> retrieveAllTodayDeliveries() {
        return deliveryRepository.findTodayDeliveries();

    }

    @Override
    public List<Delivery> retrieveWeeklyDeliveries() {
        return deliveryRepository.findThisWeekDeliveries();
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
