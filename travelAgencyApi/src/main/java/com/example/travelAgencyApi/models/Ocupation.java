package com.example.travelAgencyApi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//There is a typo in "occupation", but it's T-11 hours, I can't deal with it anymore, I don't trust the refactor.
//Maybe some programmer braver than me will hit it and I wish them good luck.
public class Ocupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String ocupationCode;
    private LocalDate checkIn;
    private LocalDate checkOut;

}
