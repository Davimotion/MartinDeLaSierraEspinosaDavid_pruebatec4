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

    //I found out what a cascade was at 22:22, 21-02-2024, 13 and a half hours before deadline. May God have mercy on the person grading this.
    //So anyway, the cascade method deletes all children entities when deleting a parent entity :)
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    private List<Room> listOfRooms;

    @OneToMany(mappedBy = "hotelBooked")
    private List<Reservation> listOfReservations;
}
