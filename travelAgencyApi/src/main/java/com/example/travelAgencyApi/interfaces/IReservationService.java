package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    void deleteReservationById(Long id);

    void saveReservation(Reservation newReservation);

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);
}
