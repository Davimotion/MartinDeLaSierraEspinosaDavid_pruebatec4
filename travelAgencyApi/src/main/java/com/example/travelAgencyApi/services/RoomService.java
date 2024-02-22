package com.example.travelAgencyApi.services;


import com.example.travelAgencyApi.interfaces.IRoomService;
import com.example.travelAgencyApi.models.Room;
import com.example.travelAgencyApi.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public void saveRoom(Room room) {
        roomRepo.save(room);
    }

    @Override
    public Optional<Room> findById(Long roomId) {
        return roomRepo.findById(roomId);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepo.findAll();
    }
}
