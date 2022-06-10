package com.example.ScheduleDelivery.service.base;

import com.example.ScheduleDelivery.model.TimeSlot;

import java.util.Set;

public interface  DeliveryService{


    void resolveAddress(String address);

    Set<TimeSlot> getTimeSlotsForAddress();

//
//● POST /timeslots - retrieve all available timeslots(See ‘Timeslot’ model) for a formatted address
//    {
//“address”: {FORMATTED ADDRESS}
//    }
//● POST /deliveries - book a delivery
//    {
//“user”: {USER},
//“timeslotId”: {TIMESLOT_ID}
//    }
//● POST /deliveries/{DELIVERY_ID}/complete - mark a delivery as completed
//● DELETE /deliveries/{DELIVERY_ID} - cancel a delivery
//● GET /deliveries/daily - retrieve all today’s deliveries
//● GET /deliveries/weekly - retrieve the deliveries for current week

}
