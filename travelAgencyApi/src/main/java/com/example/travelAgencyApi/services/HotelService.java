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
    public Optional<Hotel> findById(Long id) {
        return hotelRepo.findById(id);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepo.deleteById(id);
    }

    //method to call for when you are sure that the id you are looking for exists in the database and returns a Hotel.
    //I get the feeling that the way I'm doing this might create vulnerabilities, but there's no time for more right now.
    @Override
    public Hotel findByIdSure(Long hotelId) {
        Optional<Hotel>optionalHotel = hotelRepo.findById(hotelId);
        if(optionalHotel.isPresent()){
            Hotel hotelSure= optionalHotel.get();
            return hotelSure;
        }else {
            return null;
        }
    }
}
