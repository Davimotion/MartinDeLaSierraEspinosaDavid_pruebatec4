package com.example.travelAgencyApi.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "hotelBooked_id")
    private Hotel hotelBooked;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Room room;

    private String reservationCode;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double price;
    private Integer numPeople;
}
