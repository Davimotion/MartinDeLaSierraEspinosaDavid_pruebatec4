package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.DTOs.FlightDTO;
import com.example.travelAgencyApi.interfaces.IFlightService;
import com.example.travelAgencyApi.interfaces.ITicketService;
import com.example.travelAgencyApi.models.Flight;
import com.example.travelAgencyApi.models.Person;
import com.example.travelAgencyApi.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

    //This method returns a flight by id with a list of associated tickets and passengers.
    @GetMapping("/flights/{id}")
    public ResponseEntity <Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> optionalFlight = flightService.getFlightById(id);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            for (Ticket ticket : flight.getTicketList()) {
                ticket.setFlight(null); //we set this field to null to prevent infinite recursion.
                for (Person passenger : ticket.getPassengers()) {
                    passenger.setTicketList(Collections.emptyList());//we set this field to null to prevent infinite recursion.
                    passenger.setReservationsList(Collections.emptyList());//we set this field to null to prevent infinite recursion.
                }
            }
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //What is a cascade lol. this deletes a flight and also deletes it's associated tickets from the database.
    @DeleteMapping("/flights/{id}")
    public String deleteFlightById(@PathVariable Long id){
        Optional<Flight> optionalFlight = flightService.findById(id);
        if (optionalFlight.isPresent()) {
            Flight flightToDelete = optionalFlight.get();
            for(Ticket ticket: flightToDelete.getTicketList()){
                ticketService.deleteTicketById(ticket.getId());
            }
            flightService.deleteFlightbyId(id);
            return "Flight deleted";
        }else{
            return "No flight registered with this id";
        }


    }

    //Endpoint for editing and updating a flight in the database, the Flight's id is a @PathVariable, as that's how it appeared on the technical requirements.
    @PutMapping("/flights/edit/{id}")
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
                                             @RequestParam("priceEconomy") Double priceEconomyEdit)
                                             {
        Optional<Flight> optionalFlight = flightService.findById(id);
        if (optionalFlight.isPresent()) {
            Flight updatedFlight = optionalFlight.get();
            //editing the parameters
            updatedFlight.setFlightCode(flightCodeEdit);
            updatedFlight.setOrigin(originEdit);
            updatedFlight.setDestination(destinationEdit);
            updatedFlight.setDateOfFlight(dateOfFlightEdit);
            updatedFlight.setNumSeats(numSeatsEdit);
            updatedFlight.setNumBookedSeats(numBookedSeatsEdit);
            updatedFlight.setNumSeatsBusiness(numSeatsBusinessEdit);
            updatedFlight.setNumBookedSeatsBusiness(numBookedSeatsBusinessEdit);
            updatedFlight.setNumSeatsEconomy(numSeatsEconomyEdit);
            updatedFlight.setNumBookedSeatsEconomy(numBookedSeatsEconomyEdit);
            updatedFlight.setPriceBusiness(priceBusinessEdit);
            updatedFlight.setPriceEconomy(priceEconomyEdit);

            //Deleting the ticket list with every edit so that people aren't surprisingly assigned to a flight they didn't purchase.Also prevents infinite loops.
            for(Ticket ticket: updatedFlight.getTicketList()){
                ticketService.deleteTicketById(ticket.getId());
            }
            updatedFlight.setTicketList(Collections.emptyList());

            // Save the updated flight
           flightService.createFlight(updatedFlight);
            return ResponseEntity.ok(updatedFlight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
}
