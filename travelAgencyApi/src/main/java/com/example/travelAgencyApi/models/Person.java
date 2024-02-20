package com.example.travelAgencyApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surnames;
    private String dni;

    //    @OneToMany(mappedBy = "passenger", fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "passengers")
    private List<Ticket> ticketList = new ArrayList<>();




    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Reservation> reservationsList = new ArrayList<>();

//    @OneToMany(mappedBy = "passenger", fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Ticket> ticketList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "person", fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Reservation> reservationsList = new ArrayList<>();
}
