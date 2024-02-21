package com.example.travelAgencyApi.services;

import com.example.travelAgencyApi.interfaces.IReservationService;
import com.example.travelAgencyApi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private PersonRepository personRepo;
    @Override
    public void deleteReservationById(Long id) {
        personRepo.deleteById(id);
    }
}
