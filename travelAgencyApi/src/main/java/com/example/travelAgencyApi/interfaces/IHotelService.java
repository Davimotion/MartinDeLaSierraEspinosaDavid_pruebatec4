package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Hotel;

import java.util.List;
import java.util.Optional;

public interface IHotelService {
    void saveHotel(Hotel hotel);

    List<Hotel> findAllHotels();


    Optional<Hotel> findById(Long id);

    void deleteHotel(Long id);

    //method to call for when you are sure that the id you are looking for exists in the database and returns a Hotel.
    Hotel findByIdSure(Long hotelId);
}
