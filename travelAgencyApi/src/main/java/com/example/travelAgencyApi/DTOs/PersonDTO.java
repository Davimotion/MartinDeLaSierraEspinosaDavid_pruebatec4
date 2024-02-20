package com.example.travelAgencyApi.DTOs;

import com.example.travelAgencyApi.models.Reservation;
import com.example.travelAgencyApi.models.Ticket;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PersonDTO {
    private Long id;
    private String name;
    private String surnames;
    private String dni;
    private List<Ticket> ticketList = new ArrayList<>();
    private List<Reservation> reservationsList = new ArrayList<>();
}
