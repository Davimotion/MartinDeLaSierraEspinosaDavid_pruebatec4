package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Hotel;

import java.util.List;
import java.util.Optional;

public interface IHotelService {
    void saveHotel(Hotel hotel);

    List<Hotel> findAllHotels();


    Optional<Hotel> FindById(Long id);
}
