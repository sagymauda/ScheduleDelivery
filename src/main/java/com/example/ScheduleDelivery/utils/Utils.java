package com.example.ScheduleDelivery.utils;

import com.example.ScheduleDelivery.model.TimeSlot;

public class Utils {

    public static boolean validAvailabilityAndCapacityInTimeSlot(TimeSlot timeSlot) {
        //check Availability and capacity
        if (timeSlot.getIsAvailable().equals(false) || timeSlot.getCapacity().equals(2))
            return false;

        return true;

    }

    public static Boolean checkIfCityMatch(String timeSlotDestinationCity, String userCity) {
        if (timeSlotDestinationCity.equals(userCity))
            return true;

        return false;
    }

    public static TimeSlot makeTimeSlotAvailableAgain(TimeSlot timeSlot) {

        timeSlot.setCapacity(timeSlot.getCapacity() - 1);

        if (!timeSlot.getIsAvailable()) {
            timeSlot.setIsAvailable(true);

        }
        return timeSlot;
    }
}
