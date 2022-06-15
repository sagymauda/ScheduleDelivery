package com.example.ScheduleDelivery.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String phoneNumber;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="users_id", referencedColumnName="id")
    private Set<Address> address;

}
