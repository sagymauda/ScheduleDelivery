package com.example.ScheduleDelivery;

import com.example.ScheduleDelivery.repository.TimeSlotRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.ScheduleDelivery.model.TimeSlot;
import com.example.ScheduleDelivery.service.base.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ScheduleDeliveryApplication {

    @Autowired
    TimeSlotRepository timeSlotRepository;

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDeliveryApplication.class, args);
    }


    @Bean
    CommandLineRunner runner() {
        return args -> {
            ObjectMapper mappar = new ObjectMapper();
            TypeReference<List<TimeSlot>> typeReference = new TypeReference<List<TimeSlot>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/timeslots.json");
            try {
                List<TimeSlot> timeSlotList = mappar.readValue(inputStream, typeReference);
                timeSlotRepository.saveAll(timeSlotList);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        };


    }

}