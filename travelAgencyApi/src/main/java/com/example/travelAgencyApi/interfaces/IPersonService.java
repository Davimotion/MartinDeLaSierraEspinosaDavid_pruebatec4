package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Person;

import java.util.Optional;

public interface IPersonService {
    Optional<Person> findById(Long id);
}
