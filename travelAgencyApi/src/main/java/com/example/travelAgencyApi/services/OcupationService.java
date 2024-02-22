package com.example.travelAgencyApi.services;

import com.example.travelAgencyApi.interfaces.IOcupationService;
import com.example.travelAgencyApi.models.Ocupation;
import com.example.travelAgencyApi.repositories.OcupationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcupationService implements IOcupationService {

    @Autowired
    private OcupationRepository ocupationRepo;

    @Override
    public void saveOcupation(Ocupation ocupation) {
        ocupationRepo.save(ocupation);
    }

    @Override
    public List<Ocupation> findAll() {
        return ocupationRepo.findAll();
    }

    @Override
    public Optional<Ocupation> findById(Long id) {
        return ocupationRepo.findById(id);
    }

    @Override
    public void deleteOcupation(Long id) {
        ocupationRepo.deleteById(id);
    }
}
