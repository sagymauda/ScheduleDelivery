package com.example.ScheduleDelivery.mappar;


import com.example.ScheduleDelivery.dto.DeliveryDto;
import com.example.ScheduleDelivery.model.Delivery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DeliveryMapper {

    @Autowired
    public ModelMapper mapper;

    public DeliveryDto deliveryToDeliveryDto(Delivery delivery) {
        DeliveryDto map = mapper.map(delivery, DeliveryDto.class);
        return map;
    }

    public Set<DeliveryDto> mapDeliveryToDeliveryDto(Set<Delivery> deliveries){
        return deliveries.stream().map(this::deliveryToDeliveryDto).collect(Collectors.toSet());
    }
}
