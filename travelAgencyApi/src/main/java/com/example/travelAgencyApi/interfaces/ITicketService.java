package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Ticket;

import java.util.Optional;

public interface ITicketService {
    String bookTicket(Ticket ticket);
    //problematic method, see TicketController
    Ticket findTicketById(Long id);

    void deleteTicketById(Long id);

    Optional<Ticket> findOptionalTicketById(Long id);
}
