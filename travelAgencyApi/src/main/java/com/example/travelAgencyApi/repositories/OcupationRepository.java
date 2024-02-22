package com.example.travelAgencyApi.repositories;

import com.example.travelAgencyApi.models.Ocupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcupationRepository extends JpaRepository<Ocupation, Long> {
}
