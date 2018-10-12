package com.resort.springbootMolvenoLake.controllers.api;

import com.resort.springbootMolvenoLake.exception.NotFoundException;

import com.resort.springbootMolvenoLake.exception.NumberAlreadyExistsException;

import com.resort.springbootMolvenoLake.models.Room;

import com.resort.springbootMolvenoLake.repositories.RoomsRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("room_api_controller")

@RequestMapping("/api/room")

public class RoomController {

    // check if number is unique

    public boolean uniqueNumber(long number) {

        return (readByNumber(number) == null);

    }

    // read by number

    public Room readByNumber(long number) {

        Iterable<Room> rooms = getAllRooms();

        for (Room room : rooms) {

            if (room.getNumber() == number) {

                return room;

            }

        }

        return null;

    }

    // read room by id

    public Room readById(long id) {

        Optional<Room> room = roomsRepository.findById(id);

        if (room.isPresent()) {

            return room.get();

        } else {

            return null;

        }

    }

    @Autowired

    private RoomsRepository roomsRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)

    public Room create (@RequestBody Room newRoom){

        // Check if the roomnumber is unique

        if (uniqueNumber(newRoom.getNumber())) {

            return this.roomsRepository.save(newRoom);

        } else {

            throw new NumberAlreadyExistsException();

        }

    }

    @RequestMapping(value = "", method = RequestMethod.GET)

    public Iterable<Room> getAllRooms(){

        return this.roomsRepository.findAll();

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)

    public Optional<Room> findRoomById(@PathVariable long id){

        Optional<Room> result = roomsRepository.findById(id);

        return result;

    }

//    // UPDATE: updates room in DB

//    @RequestMapping(value = "{id}", method = RequestMethod.PUT)

//    public Room updateById(@PathVariable long id, @RequestBody Room update) {

//        if(roomsRepository.existsById(id)){

//            update.setId(id);

//            return roomsRepository.save(update);

//        } else {

//            throw new RuntimeException();

//        }

//    }

    // UPDATE: updates room in DB

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)

    public Room updateById(@PathVariable long id, @RequestBody Room newRoom) {

        Room oldRoom = readById(id);

        if (oldRoom.getNumber() == newRoom.getNumber()) {

            return this.roomsRepository.save(newRoom);

        } else {

            if (!roomsRepository.existsById(id)) {

                throw new NotFoundException();

            } else {

                if (newRoom.getNumber() != 0 && newRoom.getNumber() != oldRoom.getNumber())

                    if (uniqueNumber(newRoom.getNumber())) {

                        oldRoom.setNumber(newRoom.getNumber());

                    } else {

                        throw new NumberAlreadyExistsException();

                    }

                return this.roomsRepository.save(oldRoom);

            }

        }

    }

    // DELETE: deletes room in DB

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)

    public String deleteById(@PathVariable long id) {

        this.roomsRepository.deleteById(id);

        return "{}";

    }

}