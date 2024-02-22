package com.example.travelAgencyApi.utilities;


import com.example.travelAgencyApi.DTOs.FlightDTO;
import com.example.travelAgencyApi.interfaces.*;
import com.example.travelAgencyApi.models.Flight;
import com.example.travelAgencyApi.models.Ocupation;
import com.example.travelAgencyApi.models.Room;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TravelApiUtils {


    //method that maps Flights into FlightDTOs
    public static FlightDTO mapFlightToDTO (Flight flight){
        FlightDTO dto = new FlightDTO();
        dto.setId(flight.getId());
        dto.setFlightCode(flight.getFlightCode());
        dto.setOrigin(flight.getOrigin());
        dto.setDestination(flight.getDestination());
        dto.setDateOfFlight(flight.getDateOfFlight());
        dto.setNumSeats(flight.getNumSeats());
        dto.setNumBookedSeats(flight.getNumBookedSeats());
        dto.setNumSeatsBusiness(flight.getNumSeatsBusiness());
        dto.setNumBookedSeatsBusiness(flight.getNumBookedSeatsBusiness());
        dto.setNumSeatsEconomy(flight.getNumSeatsEconomy());
        dto.setNumBookedSeatsEconomy(flight.getNumBookedSeatsEconomy());
        dto.setPriceBusiness(flight.getPriceBusiness());
        dto.setPriceEconomy(flight.getPriceEconomy());

        return dto;
    }

    //Method that turns the Flights list from database into DTOs to prevent an infinite loop when writing JSONs
    public static List<FlightDTO> mapFlightListToFlightDTOList(List<Flight> flights) {
        List<FlightDTO> dtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDTO dto = new FlightDTO();
            dto.setId(flight.getId());
            dto.setFlightCode(flight.getFlightCode());
            dto.setOrigin(flight.getOrigin());
            dto.setDestination(flight.getDestination());
            dto.setDateOfFlight(flight.getDateOfFlight());
            dto.setNumSeats(flight.getNumSeats());
            dto.setNumBookedSeats(flight.getNumBookedSeats());
            dto.setNumSeatsBusiness(flight.getNumSeatsBusiness());
            dto.setNumBookedSeatsBusiness(flight.getNumBookedSeatsBusiness());
            dto.setNumSeatsEconomy(flight.getNumSeatsEconomy());
            dto.setNumBookedSeatsEconomy(flight.getNumBookedSeatsEconomy());
            dto.setPriceBusiness(flight.getPriceBusiness());
            dto.setPriceEconomy(flight.getPriceEconomy());
            dtos.add(dto);
        }
        return dtos;
    }

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {

        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }



}
