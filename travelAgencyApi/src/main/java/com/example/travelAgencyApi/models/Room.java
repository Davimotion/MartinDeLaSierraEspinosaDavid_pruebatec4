package com.example.travelAgencyApi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private Integer roomNum;
    private String roomType;
    private Double price;


    //Plantearse si este campo puede quedar luego vacio o asignarle otra reserva.
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservation;

    @OneToMany(mappedBy = "room")
    private List<Ocupation> ocupationList;

    private Boolean available;

}
