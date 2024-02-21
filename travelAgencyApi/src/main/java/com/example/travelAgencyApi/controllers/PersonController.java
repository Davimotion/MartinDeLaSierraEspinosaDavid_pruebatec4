package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.interfaces.IPersonService;
import com.example.travelAgencyApi.interfaces.IReservationService;
import com.example.travelAgencyApi.interfaces.ITicketService;
import com.example.travelAgencyApi.models.Flight;
import com.example.travelAgencyApi.models.Person;
import com.example.travelAgencyApi.models.Reservation;
import com.example.travelAgencyApi.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class PersonController {
    @Autowired
    private IPersonService personService;
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private IReservationService reservationService;

    @PostMapping("/persons/new")
    public String createPerson(@RequestBody Person person) {
        personService.createPerson(person);
        return "Person created successfully";
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        for (Person person : persons) {
            person.setReservationsList(Collections.emptyList());//This prevents infinite recursion
            person.setTicketList(Collections.emptyList());//This prevents infinite recursion
        }
        return persons;
    }

    @PutMapping("/persons/edit/{id}")
    public ResponseEntity<Person> editPerson(@PathVariable Long id,
                                             @RequestParam String name,
                                             @RequestParam String surnames,
                                             @RequestParam String dni) {

        Optional<Person> optionalPerson = personService.findById(id);
        if (optionalPerson.isPresent()) {
            Person personEdit = optionalPerson.get();
            personEdit.setName(name);
            personEdit.setSurnames(surnames);
            personEdit.setDni(dni);
            personService.createPerson(personEdit);

            //This prevents infinite recursion by showing the list as empty in the response, but the relationship is preserved in the database.
            personEdit.setTicketList(Collections.emptyList());
            personEdit.setReservationsList(Collections.emptyList());


            return ResponseEntity.ok(personEdit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        Optional<Person> optionalPerson = personService.findById(id);
        if (optionalPerson.isPresent()) {
            Person personDelete = optionalPerson.get();
            for (Ticket ticket : personDelete.getTicketList()) {
                ticketService.deleteTicketById(ticket.getId());
            }
            for (Reservation reservation: personDelete.getReservationsList()){
                reservationService.deleteReservationById(reservation.getId());
            }
            return "Person deleted";

        } else {
            return "Person with id " + id + " was not found in database";
        }


    }
}

//            for(Ticket ticket: personEdit.getTicketList()){
//                 Flight updatedFlight= ticket.getFlight();
//                 updatedFlight.setTicketList(Collections.emptyList());
//                 ticket.setFlight(updatedFlight);
//            }