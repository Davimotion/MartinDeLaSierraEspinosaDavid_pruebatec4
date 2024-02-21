package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.interfaces.IHotelService;
import com.example.travelAgencyApi.models.Hotel;
import com.example.travelAgencyApi.models.Person;
import com.example.travelAgencyApi.models.Reservation;
import com.example.travelAgencyApi.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class HotelController {
    @Autowired
    private IHotelService hotelService;

    @PostMapping("/hotels/new")
    public String createHotel(@RequestBody Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "Hotel created";
    }

    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        List<Hotel> hotelList = hotelService.findAllHotels();
        for (Hotel hotel : hotelList) {
            hotel.setListOfReservations(Collections.emptyList());
            hotel.setListOfRooms(Collections.emptyList());
        }
        return hotelList;
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable Long id) {
        Optional<Hotel> optionalHotel = hotelService.FindById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            for (Reservation reservation : hotel.getListOfReservations()) {
                Person booker = reservation.getPerson();
                booker.setReservationsList(Collections.emptyList());
                booker.setTicketList(Collections.emptyList());
                reservation.setHotelBooked(null);
                reservation.setRoom(null);
            }
            for (Room room : hotel.getListOfRooms()) {
                room.setReservation(Collections.emptyList());
                room.setOcupationList(Collections.emptyList());
                room.setHotel(null);
            }
            return ResponseEntity.ok(hotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/hotels/edit/{id}")
    public ResponseEntity<Hotel> editHotel(@PathVariable Long id,
                                    @RequestParam String hotelCode,
                                    @RequestParam String name,
                                    @RequestParam Boolean open,
                                    @RequestParam String country,
                                    @RequestParam String city,
                                    @RequestParam String direction,
                                    @RequestParam Integer numberOfRooms) {

        Optional<Hotel> optionalHotel = hotelService.FindById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotel.setHotelCode(hotelCode);
            hotel.setName(name);
            hotel.setOpen(open);
            hotel.setCountry(country);
            hotel.setCity(city);
            hotel.setDirection(direction);
            hotel.setNumberOfRooms(numberOfRooms);
            hotelService.saveHotel(hotel);

            //This prevents infinite recursion by showing the list as empty in the response, but the relationship is preserved in the database.
            hotel.setListOfRooms(Collections.emptyList());
            hotel.setListOfReservations(Collections.emptyList());
            return ResponseEntity.ok(hotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("hotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id){
        Optional<Hotel> hotelDelete= hotelService.FindById(id);
        if(hotelDelete.isPresent()){

        }
    }

}
