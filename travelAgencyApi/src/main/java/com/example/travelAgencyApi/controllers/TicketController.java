package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.interfaces.IFlightService;
import com.example.travelAgencyApi.interfaces.IPersonService;
import com.example.travelAgencyApi.interfaces.ITicketService;
import com.example.travelAgencyApi.models.Flight;
import com.example.travelAgencyApi.models.Person;
import com.example.travelAgencyApi.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TicketController {
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private IFlightService flightService;
    @Autowired
    private IPersonService personService;


    //Method for the creation of new plane tickets, we send a JSON with the flight and passengers object parameters empty, and they get filled with from the database.
    //Puede que falten llamamientos a edit para las person y flights(?) no estoy seguro.
    @PostMapping("/agency/flight-booking/new")
    public String bookTicket(@RequestBody Ticket ticket, @RequestParam Long flightId, @RequestParam List<Long> passengersIds) {

        //This assigns persons to the list of passengers in the ticket. If someone on the list doesn't exist in the database, the registration won't go through.
        //To properly test this in Postman, make sure to change the parameter ticketCode in the JSON.
        if (passengersIds.size() == ticket.getNumPassengers()) {
            for (Long id : passengersIds) {
                Optional<Person> optionalPassenger = personService.findById(id);
                if (optionalPassenger.isPresent()) {
                    Person passenger = optionalPassenger.get();
                    ticket.getPassengers().add(passenger);
                } else {
                    return "A person in the passengers' list is not registered in the database.";
                }
            }
        } else {
            return "There's more entries in the passengers' list that number of seats booked." +
                    " Make sure that the parameter numPassengers is the same number as the size of the List";
        }

        //This assigns a flight to the ticket through a requested parameter in the url. If there's no flight with that id in the database, the reservation won't go through.
        Optional<Flight> optionalFlight = flightService.findById(flightId);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();
            ticket.setFlight(flight);
        } else {
            return "The flight you are trying to book doesn't exist in the database";
        }


        //This calculates the final price of the reservation.
        if ((ticket.getNumSeatsEconomy() + ticket.getNumSeatsBusiness()) == ticket.getNumPassengers()) {
            Double totalprice =
                    (ticket.getNumSeatsEconomy() * ticket.getFlight().getPriceEconomy()) +
                            (ticket.getNumSeatsBusiness() * ticket.getFlight().getPriceBusiness());
            ticket.setPrice(totalprice);

            //This sends the ticket to the database and makes validations on the way and returns the diferent responses.This validation is currently not working.
            String response = ticketService.bookTicket(ticket);
            if (response.equals("An identical ticket with your code already exists in the database")) {
                return response;
            } else {
                return response + "The total price is: " + totalprice;
            }
        } else {
            return "Invalid reservation, there's a user error in the seat distribution.";
        }
    }

    //this method doesn't validate that the ticket already existed in the database before deleting because I wrote the method badly in TicketService
    //two days ago and already has plenty of uses for me to change it. I added a validation later by creating another method that returns an optional.
    @DeleteMapping("/ticket/delete/{id}")
    public String deleteTicketById(@PathVariable Long id) {
        Optional<Ticket> optionalTicket = ticketService.findOptionalTicketById(id);
        if (optionalTicket.isPresent()) {
            ticketService.deleteTicketById(id);
            return "ticket not deleted";
        } else {
            return "ticket is not in the database";

        }
    }

}