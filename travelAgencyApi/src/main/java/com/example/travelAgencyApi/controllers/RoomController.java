package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.interfaces.IHotelService;
import com.example.travelAgencyApi.interfaces.IOcupationService;
import com.example.travelAgencyApi.interfaces.IRoomService;
import com.example.travelAgencyApi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agency")
public class RoomController {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IHotelService hotelService;
    @Autowired
    private IOcupationService ocupationService;


    //method that checks the database for a valid hotel and then adds a room to the database.
    @PostMapping("/rooms/new")
    public String createRoom(@RequestBody Room room, @RequestParam Long hotelId) {
        Optional<Hotel> optionalHotel = hotelService.findById(hotelId);
        if (optionalHotel.isPresent()) {
            roomService.saveRoom(room);
            return "Room created";
        } else {
            return "No hotel with id " + hotelId + "was found in the database";
        }
    }
    //method that returns all rooms registered in the database.
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> roomList = roomService.findAll();
        for (Room room : roomList) {
            room.setHotel(null);
            room.setReservation(Collections.emptyList());
            room.setOcupationList(Collections.emptyList());

        }
        return ResponseEntity.ok(roomList);
    }





    //method that gets all the rooms that are contained in a hotel.
    @GetMapping("/rooms/from_hotel/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsFromHotel(@PathVariable Long hotelId) {
        Optional<Hotel> optionalHotel = hotelService.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            List<Room> roomList = hotel.getListOfRooms();
            for (Room room : roomList) {
                room.setHotel(null);
                room.setReservation(Collections.emptyList());
                room.setOcupationList(Collections.emptyList());
            }
            return ResponseEntity.ok(roomList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //method that returns a room by its id. also displays Ocupations and reservations.
    @GetMapping("/rooms/room_id/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {
        Optional<Room> optionalRoom = roomService.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();

            //This makes it so a recursion loop happens by setting the problematic fields to null or empty
            Hotel thisHotel = room.getHotel();
            thisHotel.setListOfRooms(Collections.emptyList());
            thisHotel.setListOfReservations(Collections.emptyList());

            for (Reservation reservation : room.getReservation()) {
                reservation.setRoom(null);
                reservation.setHotelBooked(null);

                Person thisPerson = reservation.getPerson();
                thisPerson.setTicketList(Collections.emptyList());
                thisPerson.setTicketList(Collections.emptyList());

                reservation.setPerson(thisPerson);
            }
            for (Ocupation ocupation : room.getOcupationList()) {
                ocupation.setRoom(null);
            }
            return ResponseEntity.ok(room);
        } else
            return ResponseEntity.notFound().build();
    }

    //This method finds and edits a room by id
    @PutMapping("/rooms/edit/{roomId}")
    public ResponseEntity <Room> editRoom(@PathVariable Long roomId ,
                                          @RequestParam Integer roomNum,
                                          @RequestParam String roomType,
                                          @RequestParam Double price,
                                          @RequestParam Boolean available) {
        Optional<Room> optionalRoom = roomService.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room roomEdit = optionalRoom.get();
            roomEdit.setRoomNum(roomNum);
            roomEdit.setRoomType(roomType);
            roomEdit.setPrice(price);
            roomEdit.setAvailable(available);

            roomService.saveRoom(roomEdit);
            //preparing the problematic fields for display, this doesn't affect the database and is only for display purpose.
            roomEdit.setHotel(null);
            roomEdit.setReservation(Collections.emptyList());
            for(Ocupation ocupation: roomEdit.getOcupationList()){
                ocupation.setRoom(null);
            }
            return ResponseEntity.ok(roomEdit);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rooms/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        if (roomService.findById(id).isPresent()) {
            roomService.deleteRoom(id);
            return "Room deleted";
        } else {
            return "Room " + id + " is not found in the database";
        }
    }


}

