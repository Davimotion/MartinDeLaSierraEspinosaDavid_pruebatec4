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


    @OneToMany(mappedBy = "room")
    private List<Reservation> reservation; //this field should be named as plural but it's too late and I'm scared to change it now.

    @OneToMany(mappedBy = "room",cascade = CascadeType.REMOVE)
    private List<Ocupation> ocupationList;

    private Boolean available;

}
