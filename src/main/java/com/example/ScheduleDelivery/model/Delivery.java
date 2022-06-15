package com.example.ScheduleDelivery.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address selectedAddress;

    @OneToOne()
    @JoinColumn(name = "timeslots_id", referencedColumnName = "id")
    private TimeSlot timeSlot;

    public Delivery(Status status, Address selectedAddress, TimeSlot timeSlot) {
        this.status = status;
        this.selectedAddress = selectedAddress;
        this.timeSlot = timeSlot;
    }
}
