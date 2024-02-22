package com.Junit.TravelAgenciApi;

import com.example.travelAgencyApi.DTOs.FlightDTO;
import com.example.travelAgencyApi.models.Flight;
import com.example.travelAgencyApi.utilities.TravelApiUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class UtilitiesTest {

    @Test
    public void testMapFlightToDTO() {
        // Create a Flight object for testing
        Flight flight = new Flight(123456L, "test", "test", "test", null,
                1, 1, 1, 1, 1,
                1, 15.00, 15.00, Collections.emptyList());

        // Call the static method directly
        FlightDTO testDto = TravelApiUtils.mapFlightToDTO(flight);

        // Assert each property of the DTO
        assertEquals(123456L, testDto.getId());
        assertEquals("test", testDto.getFlightCode());
        assertEquals("test", testDto.getOrigin());
        assertEquals("test", testDto.getDestination());
        assertNull(testDto.getDateOfFlight());
        assertEquals(1, testDto.getNumSeats());
        assertEquals(1, testDto.getNumBookedSeats());
        assertEquals(1, testDto.getNumSeatsBusiness());
        assertEquals(1, testDto.getNumBookedSeatsBusiness());
        assertEquals(1, testDto.getNumSeatsEconomy());
        assertEquals(1, testDto.getNumBookedSeatsEconomy());
        assertEquals(15.00, testDto.getPriceBusiness());
        assertEquals(15.00, testDto.getPriceEconomy());

    }

    @Test
    public void testMapFlightToDtoList() {
        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight(123456L, "test", "test", "test", null,
                1, 1, 1, 1, 1,
                1, 15.00, 15.00, Collections.emptyList()));
        List<FlightDTO> testList =TravelApiUtils.mapFlightListToFlightDTOList(flightList);
        for(FlightDTO testDto: testList){
            assertEquals(123456L, testDto.getId());
            assertEquals("test", testDto.getFlightCode());
            assertEquals("test", testDto.getOrigin());
            assertEquals("test", testDto.getDestination());
            assertNull(testDto.getDateOfFlight());
            assertEquals(1, testDto.getNumSeats());
            assertEquals(1, testDto.getNumBookedSeats());
            assertEquals(1, testDto.getNumSeatsBusiness());
            assertEquals(1, testDto.getNumBookedSeatsBusiness());
            assertEquals(1, testDto.getNumSeatsEconomy());
            assertEquals(1, testDto.getNumBookedSeatsEconomy());
            assertEquals(15.00, testDto.getPriceBusiness());
            assertEquals(15.00, testDto.getPriceEconomy());

        }
    }

    @Test
    public void testGetDatesBetween() {
        // Test input
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 5);

        // Expected output
        List<LocalDate> expectedDates = List.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 3),
                LocalDate.of(2024, 1, 4)
        );

        // Actual output
        List<LocalDate> actualDates = TravelApiUtils.getDatesBetween(startDate, endDate);

        // Assertion
        assertEquals(expectedDates, actualDates);
    }

}
