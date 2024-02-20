package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.DTOs.FlightDTO;
import com.example.travelAgencyApi.interfaces.IFlightService;
import com.example.travelAgencyApi.interfaces.ITicketService;
import com.example.travelAgencyApi.models.Flight;
import com.example.travelAgencyApi.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class FlightController {

    @Autowired
    private IFlightService flightService;
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/flights")
    public List<FlightDTO> getAllRegisteredFlights(){
        return flightService.getAllRegisteredFlights();
    }

    @GetMapping("/flights/filtered")
    public List<FlightDTO> getFlightsByParams(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo, @RequestParam String origin, @RequestParam String destination){
        return flightService.geFlightsByParams(dateFrom,dateTo,origin,destination);
    }



    //A partir de aqui métodos a asegurar con Spring Security.

    @PostMapping("/flights/new")
    public String createFlight (@RequestBody Flight flight){
        flightService.createFlight(flight);
        return "Flight registered";
    }

    @GetMapping("/flights/{id}")
    public FlightDTO getFlightById(@PathVariable Long id){
        return flightService.getFlightById(id);
    }

    @DeleteMapping("/flights/{id}")
    public String deleteFlightById(@PathVariable Long id){
        flightService.deleteFlightbyId(id);
        return "Flight deleted";
    }
    @PutMapping("/agency/flights/edit/{id}")
    public ResponseEntity<Flight> editFlight(@PathVariable Long id,
                                             @RequestParam("flightCode") String flightCodeEdit,
                                             @RequestParam("origin") String originEdit,
                                             @RequestParam("destination") String destinationEdit,
                                             @RequestParam("dateOfFlight") LocalDate dateOfFlightEdit,
                                             @RequestParam("numSeats") Integer numSeatsEdit,
                                             @RequestParam("numBookedSeats") Integer numBookedSeatsEdit,
                                             @RequestParam("numSeatsBusiness") Integer numSeatsBusinessEdit,
                                             @RequestParam("numBookedSeatsBusiness") Integer numBookedSeatsBusinessEdit,
                                             @RequestParam("numSeatsEconomy") Integer numSeatsEconomyEdit,
                                             @RequestParam("numBookedSeatsEconomy") Integer numBookedSeatsEconomyEdit,
                                             @RequestParam("priceBusiness") Double priceBusinessEdit,
                                             @RequestParam("priceEconomy") Double priceEconomyEdit,
                                             @RequestParam("ticketList") List<Long> ticketIds) {
        Optional<Flight> optionalFlight = flightService.findById(id);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();

            flight.setFlightCode(flightCodeEdit);
            flight.setOrigin(originEdit);
            flight.setDestination(destinationEdit);
            flight.setDateOfFlight(dateOfFlightEdit);
            flight.setNumSeats(numSeatsEdit);
            flight.setNumBookedSeats(numBookedSeatsEdit);
            flight.setNumSeatsBusiness(numSeatsBusinessEdit);
            flight.setNumBookedSeatsBusiness(numBookedSeatsBusinessEdit);
            flight.setNumSeatsEconomy(numSeatsEconomyEdit);
            flight.setNumBookedSeatsEconomy(numBookedSeatsEconomyEdit);
            flight.setPriceBusiness(priceBusinessEdit);
            flight.setPriceEconomy(priceEconomyEdit);

            // Update ticketList if needed
            if (ticketIds != null && !ticketIds.isEmpty()) {
                for(Long id: ticketIds){
                flight.getTicketList().add()ticketService.findTicketById(id);

            }

            // Save the updated flight
            Flight updatedFlight = flightService.createFlight(flight);
            return ResponseEntity.ok(updatedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //TODO POST: /agency/flights/new
    //
    //PUT: /agency/flights/edit/{id}
    //
    //GET: /agency/flights → Todos los vuelos
}
