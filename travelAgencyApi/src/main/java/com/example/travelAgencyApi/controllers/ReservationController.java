package com.example.travelAgencyApi.controllers;

import com.example.travelAgencyApi.interfaces.*;
import com.example.travelAgencyApi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.travelAgencyApi.utilities.TravelApiUtils.getDatesBetween;

@RestController
@RequestMapping("/agency")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IHotelService hotelService;
    @Autowired
    private IOcupationService ocupationService;
    @Autowired
    private IPersonService personService;

    //This endpoint is a disaster that could have been prevented when making the entity classes.
    @GetMapping("/reservations/new")
    public String createReservation(@RequestBody Reservation InputReservation,
                                    @RequestParam Long personId,
                                    @RequestParam Long hotelId,
                                    @RequestParam Long roomId) {
        if (personService.findById(personId).isPresent() && hotelService.findById(hotelId).isPresent() &&
                roomService.findById(roomId).isPresent()) {
            Reservation newReservation = InputReservation;
            LocalDate dateFrom = newReservation.getCheckInDate();
            LocalDate dateTo = newReservation.getCheckOutDate();

            //this mess validates if the room is available. It should be a utility function, but it isn't.
            Hotel testHotel = hotelService.findByIdSure(roomId);
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

                // If isDateAvailable is still true after checking all dates, continue with the room type validation.
                if (isDateAvailable) {

                    switch (newReservation.getNumPeople()) {
                        case 1:
                            if(testRoom.getRoomType().equalsIgnoreCase("Single")) {
                                newReservation.setPrice(testRoom.getPrice());
                                reservationService.saveReservation(newReservation);
                                return "reservation created for person " + personId + "in hotel" + testHotel.getName() + " ,id: " + testHotel.getId() +
                                        "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo + " in room id: " + testRoom.getId() + " number: " +
                                        testRoom.getRoomNum() + " type: " + testRoom.getRoomType() + " with PRICE: " + newReservation.getPrice();
                            }else{
                                return "No room of type 'Single' is available at hotel: "+ testHotel.getName() + " ,id: " + testHotel.getId() +
                                        "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo;
                            }
                        case 2:
                            if(testRoom.getRoomType().equalsIgnoreCase("Double")) {
                                newReservation.setPrice(testRoom.getPrice());
                                reservationService.saveReservation(newReservation);
                                return "reservation created for person " + personId + "in hotel" + testHotel.getName() + " ,id: " + testHotel.getId() +
                                        "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo + " in room id: " + testRoom.getId() + " number: " +
                                        testRoom.getRoomNum() + " type: " + testRoom.getRoomType() + " with PRICE: " + newReservation.getPrice();
                            }else{
                                return "No room of type 'Double' is available at hotel: "+ testHotel.getName() + " ,id: " + testHotel.getId() +
                                        "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo;
                            }
                        case 3:
                            if(testRoom.getRoomType().equalsIgnoreCase("Triple")) {
                                newReservation.setPrice(testRoom.getPrice());
                                reservationService.saveReservation(newReservation);
                                return "reservation created for person " + personId + "in hotel" + testHotel.getName() + " ,id: " + testHotel.getId() +
                                        "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo + " in room id: " + testRoom.getId() + " number: " +
                                        testRoom.getRoomNum() + " type: " + testRoom.getRoomType() + " with PRICE: " + newReservation.getPrice();
                            }else{
                                return "No room of type 'Triple' is available at hotel: "+ testHotel.getName() + " ,id: " + testHotel.getId() +
                                        "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo;
                            }
                        default:
                            if(newReservation.getNumPeople() > 3){
                                if(testRoom.getRoomType().equalsIgnoreCase("Multiple")) {
                                    newReservation.setPrice(testRoom.getPrice());
                                    reservationService.saveReservation(newReservation);
                                    return "reservation created for person " + personId + "in hotel" + testHotel.getName() + " ,id: " + testHotel.getId() +
                                            "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo + " in room id: " + testRoom.getId() + " number: " +
                                            testRoom.getRoomNum() + " type: " + testRoom.getRoomType() + " with PRICE: " + newReservation.getPrice();
                                }else{
                                    return "No room of type 'Double' is available at hotel: "+ testHotel.getName() + " ,id: " + testHotel.getId() +
                                            "code " + testHotel.getHotelCode() + " ,from " + dateFrom + " to " + dateTo;
                                }
                            }
                            else{
                                return "invalid number of people in variable numPeople on the given JSOn body, must be an Integer that's a positive number.";
                            }
                    }
                }
                return "reservation invalid, there's no room available in the hotel" +testHotel.getName()+" ,id: " +testHotel.getId()+ "for the given dates";
            }
        }

    return "One or more of the parameters of the request in the url are invalid.";
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations (){
        List<Reservation> reservationList = reservationService.findAll();

        //cleaning problematic parameters for display.
        for (Reservation reservation: reservationList){
            Person thisPerson = reservation.getPerson();
                thisPerson.setReservationsList(Collections.emptyList());
                thisPerson.setTicketList(Collections.emptyList());
            Hotel thisHotel= reservation.getHotelBooked();
                thisHotel.setListOfReservations(Collections.emptyList());
                thisHotel.setListOfRooms(Collections.emptyList());
            Room thisRoom = reservation.getRoom();
                thisRoom.setReservation(Collections.emptyList());
                thisRoom.setHotel(null);
                thisRoom.setOcupationList(Collections.emptyList());
        }
        return reservationList;
    }
    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id){
        Optional <Reservation> optionalReservation = reservationService.findById(id);
        if(optionalReservation.isPresent()){
            Reservation reservation= optionalReservation.get();
            //cleaning problematic parameters for display.
            Person thisPerson = reservation.getPerson();
            thisPerson.setReservationsList(Collections.emptyList());
            thisPerson.setTicketList(Collections.emptyList());
            Hotel thisHotel= reservation.getHotelBooked();
            thisHotel.setListOfReservations(Collections.emptyList());
            thisHotel.setListOfRooms(Collections.emptyList());
            Room thisRoom = reservation.getRoom();
            thisRoom.setReservation(Collections.emptyList());
            thisRoom.setHotel(null);
            thisRoom.setOcupationList(Collections.emptyList());

            return ResponseEntity.ok(reservation);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/reservations/{id}")
    public String deleteReservation (@PathVariable Long id){
        Optional<Reservation> optionalReservation =reservationService.findById(id);
        if(optionalReservation.isPresent()){
        reservationService.deleteReservationById(id);
        return "Reservation deleted";}
        else{
            return "Reservation not found in the database";
        }
    }

}


