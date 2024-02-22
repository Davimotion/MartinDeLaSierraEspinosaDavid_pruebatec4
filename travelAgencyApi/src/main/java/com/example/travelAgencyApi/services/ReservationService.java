package com.example.travelAgencyApi.services;

import com.example.travelAgencyApi.interfaces.IReservationService;
import com.example.travelAgencyApi.models.Reservation;
import com.example.travelAgencyApi.repositories.PersonRepository;
import com.example.travelAgencyApi.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private ReservationRepository reservationRepo;
    @Override
    public void deleteReservationById(Long id) {
        reservationRepo.deleteById(id);
    }

    @Override
    public void saveReservation(Reservation newReservation) {
        reservationRepo.save(newReservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepo.findById(id);
    }
}
