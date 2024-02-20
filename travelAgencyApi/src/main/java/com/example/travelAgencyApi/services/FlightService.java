package com.example.travelAgencyApi.services;

import com.example.travelAgencyApi.DTOs.FlightDTO;
import com.example.travelAgencyApi.interfaces.IFlightService;
import com.example.travelAgencyApi.models.Flight;
import com.example.travelAgencyApi.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.travelAgencyApi.utilities.TravelApiUtils.*;

@Service
public class FlightService implements IFlightService {

    @Autowired
    private FlightRepository flightRepo;


    @Override
    public List<FlightDTO> getAllRegisteredFlights() {
        return mapFlightListToFlightDTOList(flightRepo.findAll());

    }

    @Override
    public List<FlightDTO> geFlightsByParams(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {

        List<FlightDTO> dtos = mapFlightListToFlightDTOList(flightRepo.findAll());

        List<LocalDate> dates = getDatesBetween(dateFrom, dateTo);

        List<FlightDTO> filteredFlights = new ArrayList<>();

        for (FlightDTO flight : dtos) {
            if (flight.getOrigin().equals(origin) & flight.getDestination().equals(destination)) {
                // Check if the dateOfFlight of the flight DTO falls within the specified date range
                LocalDate flightDate = flight.getDateOfFlight();
                for (LocalDate date : dates) {
                    if (flightDate.equals(date)) {
                        filteredFlights.add(flight);
                    }
                }
            }
        }
        return filteredFlights;
    }

    @Override
    public void createFlight(Flight flight) {
        flightRepo.save(flight);
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        Optional<Flight> optionalFlight = flightRepo.findById(id);
        if(optionalFlight.isPresent()){
            Flight flight = optionalFlight.get(); // Extracting the Flight object from Optional
            return mapFlightToDTO(flight); // Mapping Flight to FlightDTO
        }else{
            return null;
        }


    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightRepo.findById(id);
    }

    @Override
    public void deleteFlightbyId(Long id) {
        flightRepo.deleteById(id);
    }

}
