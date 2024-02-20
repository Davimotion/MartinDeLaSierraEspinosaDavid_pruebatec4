package com.example.travelAgencyApi.DTOs;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FlightDTO {
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
}
