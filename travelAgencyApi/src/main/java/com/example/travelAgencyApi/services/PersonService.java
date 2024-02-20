package com.example.travelAgencyApi.services;

import com.example.travelAgencyApi.interfaces.IPersonService;
import com.example.travelAgencyApi.models.Person;
import com.example.travelAgencyApi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepo;

    @Override
    public Optional<Person> findById(Long id) {
        return personRepo.findById(id);
    }
}
