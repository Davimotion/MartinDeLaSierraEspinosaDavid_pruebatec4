package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.interfaces.IOcupationService;
import com.example.travelAgencyApi.models.Hotel;
import com.example.travelAgencyApi.models.Ocupation;
import com.example.travelAgencyApi.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class OcupationController {

    @Autowired
    private IOcupationService ocupationService;

    @PostMapping("/ocupations/new")
    public String createOcupation(@RequestBody Ocupation ocupation){
        ocupationService.saveOcupation(ocupation);
        return "Ocupation has been created";
    }
    //Method to return the full list of ocupations as well as the room and hotel associated with them. Behold the spaghetti.
    //I probably should have used a miriad of DTOs to display all the data, but IDK if that would have been even worse tbh... maybe a billion utilities.
    //Commenting this method took almost as long as writing it, and it's not even tested yet.
    @GetMapping("/ocupations")
    public ResponseEntity<List<Ocupation>> getAllOcupations() {
        List<Ocupation> ocupationList = ocupationService.findAll();

        //This ball of spaghetti is for displaying the desired data and to nat have recursion loops when writing JSONs.
        //This first loop modifies all the ocupations and empties the problematic fields.

        //each time a loop iteration is started an auxiliary Room is created to modify problematic fields
        for(Ocupation ocupation: ocupationList){
            //Auxiliary Room created to help modify problematic data for display
            Room thisRoom = ocupation.getRoom(); // Ocupation >> Room
                thisRoom.setOcupationList(Collections.emptyList());
                thisRoom.setReservation(Collections.emptyList());
            //Auxiliary Hotel created to help modify problematic data for display
            Hotel thisRoomHotel =thisRoom.getHotel();// Ocupation >> Room >> Hotel
                    thisRoomHotel.setListOfReservations(Collections.emptyList());
                    //For each Room of the auxiliary hotel the problematic fields are emptied
                    for(Room hotelRoom: thisRoomHotel.getListOfRooms()){
                        hotelRoom.setReservation(Collections.emptyList());
                        hotelRoom.setOcupationList(Collections.emptyList());
                        hotelRoom.setHotel(null);
                    }thisRoom.setHotel(thisRoomHotel);// Ocupation >> Room << Hotel
            ocupation.setRoom(thisRoom);// Ocupation << Room
        }
        return ResponseEntity.ok(ocupationList);
    }

    //This method finds and displays an ocupation by id reusing some of the previous spaghetti that really should have been its own method in utilities.
    @GetMapping("/ocupations/{id}")
    public ResponseEntity<Ocupation> getOcupationById(Long id){
        Optional<Ocupation> optionalOcupation= ocupationService.findById(id);
        if(optionalOcupation.isPresent()){
            Ocupation ocupation = optionalOcupation.get();
            //Auxiliary Room created to help modify problematic data for display
            Room thisRoom = ocupation.getRoom(); // Ocupation >> Room
            thisRoom.setOcupationList(Collections.emptyList());
            thisRoom.setReservation(Collections.emptyList());
            //Auxiliary Hotel created to help modify problematic data for display
            Hotel thisRoomHotel =thisRoom.getHotel();// Ocupation >> Room >> Hotel
            thisRoomHotel.setListOfReservations(Collections.emptyList());
            //For each Room of the auxiliary hotel the problematic fields are emptied
            for(Room hotelRoom: thisRoomHotel.getListOfRooms()){
                hotelRoom.setReservation(Collections.emptyList());
                hotelRoom.setOcupationList(Collections.emptyList());
                hotelRoom.setHotel(null);
            }thisRoom.setHotel(thisRoomHotel);// Ocupation >> Room << Hotel
            ocupation.setRoom(thisRoom);// Ocupation << Room

            return ResponseEntity.ok(ocupation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/ocupations/edit/{id}")
    public ResponseEntity<Ocupation> editOcupation (@PathVariable Long id ,
                                                    @RequestParam String ocupationCode,
                                                    @RequestParam LocalDate checkIn,
                                                    @RequestParam LocalDate checkOut){
        Optional<Ocupation> optionalOcupation= ocupationService.findById(id);
        if(optionalOcupation.isPresent()) {
            Ocupation ocupation = optionalOcupation.get();
            ocupation.setOcupationCode(ocupationCode);
            ocupation.setCheckIn(checkIn);
            ocupation.setCheckOut(checkOut);
            ocupationService.saveOcupation(ocupation);

            //setting to null for display
            ocupation.setRoom(null);
            return ResponseEntity.ok(ocupation);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("ocupations/delete/{id}")
    public String deleteOcupation (@PathVariable Long id){
        Optional<Ocupation> optionalOcupation = ocupationService.findById(id);
        if(optionalOcupation.isPresent()) {
            Ocupation ocupation = optionalOcupation.get();
            ocupationService.deleteOcupation(id);
            return "Ocupation deleted";
        }else{
            return "The ocupation "+id+ " is not found in the database";
        }
    }





}
