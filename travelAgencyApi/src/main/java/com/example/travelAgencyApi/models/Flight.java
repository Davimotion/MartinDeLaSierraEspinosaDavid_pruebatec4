package com.example.travelAgencyApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightCode;
    private String origin;
    private String destination;
    private LocalDate dateOfFlight;
    private Integer numSeats;
    private Integer numBookedSeats;
    private Integer numSeatsBusiness;
    private Integer numBookedSeatsBusiness;
    private Integer numSeatsEconomy;
    private Integer numBookedSeatsEconomy;
    private Double priceBusiness;
    private Double priceEconomy;

    @OneToMany(mappedBy = "flight")
    @ToString.Exclude
    private List<Ticket> ticketList;
}

//@JsonIgnore
//@ToString.Exclude