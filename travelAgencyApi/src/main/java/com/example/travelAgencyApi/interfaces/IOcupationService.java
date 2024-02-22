package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Ocupation;

import java.util.List;
import java.util.Optional;

public interface IOcupationService {
    void saveOcupation(Ocupation ocupation);

    List<Ocupation> findAll();

    Optional<Ocupation> findById(Long id);


    void deleteOcupation(Long id);
}
