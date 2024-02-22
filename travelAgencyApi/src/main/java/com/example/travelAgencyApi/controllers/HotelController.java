package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.interfaces.IHotelService;
import com.example.travelAgencyApi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.travelAgencyApi.utilities.TravelApiUtils.getDatesBetween;

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

    @GetMapping("/hotels/get_available")
    public List<Hotel> getAvailableHotels(@RequestParam LocalDate dateFrom,
                                          @RequestParam LocalDate dateTo) {

        //List and loop to validate if the hotel is available
        List<Hotel> testHotelList = hotelService.findAllHotels();

        List<Hotel> availableHotelslist = new ArrayList<>();
//            for(Hotel hotel: testHotelList){
//                if(hotel.getOpen().equals(true)){
//                    openHotellist.add(hotel);
//                }
//            }
        //First validation to test if a Hotel is open, then get its id and add it to a list.
        List<Long> listIds = new ArrayList<>();
        for (Hotel hotel : hotelService.findAllHotels()) {
            if (hotel.getOpen().equals(true)) {
                listIds.add(hotel.getId());
            }
        }

        for (Long hotelId : listIds) {
            Hotel testHotel = hotelService.findByIdSure(hotelId);
            for (Room testRoom : testHotel.getListOfRooms()) {
                boolean isDateAvailable = testRoom.getAvailable(); // Initialize isDateAvailable based on room availability
                if (isDateAvailable) {
                    for (Ocupation ocupation : testRoom.getOcupationList()) {
                        List<LocalDate> datesToCheck = getDatesBetween(ocupation.getCheckIn(), ocupation.getCheckOut());
                        for (LocalDate checkDate : datesToCheck) {
                            if (dateFrom.equals(checkDate) && dateTo.equals(checkDate)) {
                                isDateAvailable = false; // Set to false if date is occupied
                                break; // No need to continue checking if date is already occupied
                            }
                        }
                    }
                }
                // If isDateAvailable is still true after checking all dates, add the hotel to the available list
                if (isDateAvailable) {
                    availableHotelslist.add(testHotel);
                }
            }
        }
        return availableHotelslist;
    }


    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable Long id) {
        Optional<Hotel> optionalHotel = hotelService.findById(id);
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

    //This method edits a hotel in the database, but respects all Rooms and Reservations associated with it. To add to the lists, use the create methods in the respective controllers.
    @PutMapping("/hotels/edit/{id}")
    public ResponseEntity<Hotel> editHotel(@PathVariable Long id,
                                           @RequestParam String hotelCode,
                                           @RequestParam String name,
                                           @RequestParam Boolean open,
                                           @RequestParam String country,
                                           @RequestParam String city,
                                           @RequestParam String direction,
                                           @RequestParam Integer numberOfRooms) {

        Optional<Hotel> optionalHotel = hotelService.findById(id);
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

    //this method is really simple because I found out what a cascade was :)
    //This method deletes a specific hotel by id.
    @DeleteMapping("hotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        Optional<Hotel> hotelDelete = hotelService.findById(id);
        if (hotelDelete.isPresent()) {
            hotelService.deleteHotel(id);
            return "Hotel deleted";
        } else {
            return "Hotel wit id " + id + " was not found in database";
        }

    }

}
//Previous validation for getAvailableIds() that I'm keeping here because I'm scared to delete it.
//        for (Long hotelId: listIds){
//            Hotel testHotel = hotelService.findByIdSure(hotelId);
//            for(Room testRoom: testHotel.getListOfRooms()){
//                if(testRoom.getAvailable().equals(true)){
//                    boolean isDateAvailable = true; // Auxiliary boolean to trigger the next loop.
//                    //If there's a matching date, it stops the validations.
//                    if(isDateAvailable) {
//                        for (Ocupation ocupation : testRoom.getOcupationList()) {
//                            List<LocalDate> datesToCheck = getDatesBetween(ocupation.getCheckIn(), ocupation.getCheckOut());//using a utilities method to get a list of dates in between.
//                            for (LocalDate checkDate: datesToCheck){
//                                if(dateFrom.equals(checkDate) && dateTo.equals(checkDate)){
//                                    isDateAvailable = !isDateAvailable;
//                                }else{
//                                    availableHotelslist.add(testHotel);
//
//                                }
//                            }
//                        }
//                    }
//                }
//
//            }
//        }