package com.resort.springbootMolvenoLake.controllers.api;


import com.resort.springbootMolvenoLake.services.GuestService;
import com.resort.springbootMolvenoLake.models.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("guest_api_controller")
@RequestMapping("/api/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Guest> get() {
        return this.guestService.all();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Guest> getSingle(@PathVariable long id) {
        return this.guestService.read(id);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Guest add(@RequestBody Guest order) {
        this.guestService.create(order);
        return order;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Guest update(@RequestBody Guest order) {
        return this.guestService.update(order);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable long id) {
        if (id >= 0) {
            this.guestService.delete(id);
            return true;
        } else {
            return false;
        }
    }
}
