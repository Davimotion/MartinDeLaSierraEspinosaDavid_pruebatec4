package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.DTOs.FlightDTO;
import com.example.travelAgencyApi.models.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IFlightService {


    public List<FlightDTO> getAllRegisteredFlights();


    List<FlightDTO> geFlightsByParams(LocalDate dateFrom, LocalDate dateTo, String origin, String destination);

    void createFlight(Flight flight);


    FlightDTO getFlightById(Long id);

    Optional<Flight> findById(Long id);

    void deleteFlightbyId(Long id);
}
