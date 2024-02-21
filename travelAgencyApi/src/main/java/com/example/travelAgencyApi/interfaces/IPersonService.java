package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    List<Person> getAllPersons();

    Optional<Person> findById(Long id);

    void createPerson(Person person);
}
