package com.example.travelAgencyApi.services;

import com.example.travelAgencyApi.interfaces.IHotelService;
import com.example.travelAgencyApi.models.Hotel;
import com.example.travelAgencyApi.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public void saveHotel(Hotel hotel) {
        hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> findAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Optional<Hotel> FindById(Long id) {
        return hotelRepo.findById(id);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepo.deleteById(id);
    }
}
