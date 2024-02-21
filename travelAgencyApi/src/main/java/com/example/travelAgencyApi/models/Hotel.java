package com.example.travelAgencyApi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelCode;
    private String name;
    private Boolean open;
    private String country;
    private String city;
    private String direction;
    private Integer numberOfRooms;

    @OneToMany(mappedBy = "hotel")
    private List<Room> listOfRooms;

    @OneToMany(mappedBy = "hotelBooked")
    private List<Reservation> listOfReservations;
}
