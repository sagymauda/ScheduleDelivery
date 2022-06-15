package com.example.ScheduleDelivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "timeslots")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endTime;

    @NotNull
    private String supportedCity;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    private Integer capacity;

    public TimeSlot(Date startTime, Date endTime, String supportedCity, Boolean isAvailable) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.supportedCity = supportedCity;
        this.isAvailable = isAvailable;
    }
}
