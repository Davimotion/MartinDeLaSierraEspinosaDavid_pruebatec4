package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Ticket;

public interface ITicketService {
    String bookTicket(Ticket ticket);

    Ticket findTicketById(Long id);

    void deleteTicketById(Long id);
}
