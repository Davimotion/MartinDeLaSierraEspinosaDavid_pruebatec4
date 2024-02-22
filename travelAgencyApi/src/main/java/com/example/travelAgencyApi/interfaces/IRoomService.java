package com.example.travelAgencyApi.interfaces;

import com.example.travelAgencyApi.models.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService {
    void saveRoom(Room room);

    Optional<Room> findById(Long roomId);

    void deleteRoom(Long id);

    List<Room> findAll();
}
