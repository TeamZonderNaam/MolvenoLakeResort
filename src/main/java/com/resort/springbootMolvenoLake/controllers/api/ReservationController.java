package com.resort.springbootMolvenoLake.controllers.api;

import com.resort.springbootMolvenoLake.models.Reservation;
import com.resort.springbootMolvenoLake.models.TimeSlot;
import com.resort.springbootMolvenoLake.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Reservation> get() {
        return service.all();
    }

    @GetMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Reservation getSingle(@PathVariable(name="id") int id) {
        return service.read(id);
    }

    @GetMapping(value = "ymd/{year}-{month}-{day}-{numberOfPersons}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TimeSlot> getAvailableTimeSlots(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day, @PathVariable int numberOfPersons) {
        LocalDate reservationDate = LocalDate.of(year, month, day);
        System.out.println("The date of the reservation is: " + reservationDate);
        return service.availableTimeSlots(reservationDate, numberOfPersons);
    }

    @PostMapping(value = "/", produces=MediaType.APPLICATION_JSON_VALUE)
    public Reservation create(@RequestBody Reservation reservation) {
        long id = service.create(reservation);
        return service.read((int)id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation update(@PathVariable int id, @RequestBody Reservation reservation) {
        return service.update(reservation);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
