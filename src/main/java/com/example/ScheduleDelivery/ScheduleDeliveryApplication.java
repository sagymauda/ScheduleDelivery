package com.example.ScheduleDelivery;

import com.example.ScheduleDelivery.model.TimeSlot;
import com.example.ScheduleDelivery.repository.TimeSlotRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public ModelMapper modelMapper() {
        return new ModelMapper();
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