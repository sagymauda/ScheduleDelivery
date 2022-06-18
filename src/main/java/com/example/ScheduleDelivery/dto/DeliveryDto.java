package com.example.ScheduleDelivery.dto;

import com.example.ScheduleDelivery.model.Address;
import com.example.ScheduleDelivery.model.Status;
import com.example.ScheduleDelivery.model.TimeSlot;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DeliveryDto implements Serializable {

    @Null
    private Long id;


    @NotNull
    private Status status;

    @NotNull
    private Address selectedAddress;


    @NotNull
    private TimeSlot timeSlot;
}
