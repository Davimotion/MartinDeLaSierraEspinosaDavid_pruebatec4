package com.example.travelAgencyApi.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private String ticketCode;
    //cambiar a clase Integer
    private Integer numPassengers;
    private Integer numSeatsEconomy;
    private Integer numSeatsBusiness;

    //    @ManyToOne
//    @JoinColumn(name = "person_id")
//    private Person passenger;
    @ManyToMany
    @JoinTable(
            name = "ticket_passenger",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    List<Person> passengers;


    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;


}
