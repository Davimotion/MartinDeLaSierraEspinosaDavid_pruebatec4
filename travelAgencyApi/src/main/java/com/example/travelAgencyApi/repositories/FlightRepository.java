package com.example.travelAgencyApi.repositories;

import com.example.travelAgencyApi.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

//    public List<Flight> findByOriginAndDestination


}
