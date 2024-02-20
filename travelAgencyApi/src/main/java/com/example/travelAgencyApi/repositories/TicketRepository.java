package com.example.travelAgencyApi.repositories;

import com.example.travelAgencyApi.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByCode(String ticketCode);


//    Optional<Ticket> findById();
}
