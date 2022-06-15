package com.example.ScheduleDelivery.utils;

import com.example.ScheduleDelivery.model.TimeSlot;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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

    public static boolean isSameDayUsingInstant(Date date1, Date date2) {
        Instant instant1 = date1.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        Instant instant2 = date2.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        return instant1.equals(instant2);
    }

    @SneakyThrows
    public static Date createCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.parse(formatter.format(date));
    }

    public static TimeSlot makeTimeSlotAvailableAgain(TimeSlot timeSlot) {

        timeSlot.setCapacity(timeSlot.getCapacity() - 1);

        if (!timeSlot.getIsAvailable()) {
            timeSlot.setIsAvailable(true);

        }
        return timeSlot;
    }
}
