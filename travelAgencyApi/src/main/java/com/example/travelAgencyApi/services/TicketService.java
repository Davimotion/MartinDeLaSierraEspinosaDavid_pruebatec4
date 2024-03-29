package com.example.travelAgencyApi.services;

import com.example.travelAgencyApi.interfaces.ITicketService;
import com.example.travelAgencyApi.models.Ticket;
import com.example.travelAgencyApi.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository ticketRepo;

    @Override //TODO fix this mess of a validation, possibly move it to the controller. why does it let me make the same ticket twice but only twice???
            //And after that it no longer lets me create a ticket with the same ticketCode in another flight, why???
    public String bookTicket(Ticket ticket) {
        if (ticket.equals(ticketRepo.findByTicketCode(ticket.getTicketCode()))) {
            return "An identical ticket with your code already exists in the database";
        } else {
            //Validation that there are enough seats on the flight for the reservation to be possible,
            if (
                    ticket.getNumPassengers() <= (ticket.getFlight().getNumSeats() - ticket.getFlight().getNumBookedSeats()) &&
                            ticket.getNumSeatsEconomy() <= (ticket.getFlight().getNumSeatsEconomy() - ticket.getFlight().getNumBookedSeatsEconomy()) &&
                            ticket.getNumSeatsBusiness() <= (ticket.getFlight().getNumSeatsBusiness() - ticket.getFlight().getNumBookedSeatsBusiness())
            ) {
                ticketRepo.save(ticket);
                return "Your reservation has been processed.";
            } else {
                return "Not enough seats available at the flight";
            }
        }
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteTicketById(Long id) {
        ticketRepo.deleteById(id);
    }

    @Override
    public Optional<Ticket> findOptionalTicketById(Long id) {
        return ticketRepo.findById(id);
    }
}

