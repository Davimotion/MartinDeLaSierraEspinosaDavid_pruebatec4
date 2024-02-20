package com.example.travelAgencyApi.DTOs;

import com.example.travelAgencyApi.models.Person;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TicketDTO {
    private Long id;
    private Double price;
    private int numPassengers;
    List<Person> passengers;
}
