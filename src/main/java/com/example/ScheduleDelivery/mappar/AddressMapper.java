package com.example.ScheduleDelivery.mappar;

import com.example.ScheduleDelivery.dto.AddressDto;
import com.example.ScheduleDelivery.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    @Autowired
    public ModelMapper mapper;

    public Address addressDtoToAddress(AddressDto addressDto) {
        Address address = mapper.map(addressDto, Address.class);
        return address;
    }

}
