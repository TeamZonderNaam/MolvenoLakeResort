package com.resort.springbootMolvenoLake.controllers;

import com.resort.springbootMolvenoLake.models.Guest;
import com.resort.springbootMolvenoLake.repositories.GuestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("hotel/guest/")
public class GuestsController extends Controller
{
    @Autowired
    private GuestsRepository guestsRepository;

    // GET: Returns all suppliers
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Guest> getAll() {
        return guestsRepository.findAll();
    }

    // POST: that saves supplier in DB
    @RequestMapping(method = RequestMethod.POST)
    public Guest create(@RequestBody Guest guest) {
        return guestsRepository.save(guest);
    }

    // GET: request that returns one supplier
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<Guest> findById(@PathVariable long id) {
        Optional<Guest> result = guestsRepository.findById(id);
        return result;
    }

    // UPDATE: updates supplier in DB
    //@RequestMapping(value = "{id}", method = RequestMethod.PUT)
    //public Guest updateById(@PathVariable long id, @RequestBody Guest update) {
    //    if(guestsRepository.existsById(id)){
    //        update.
    //        return guestsRepository.save(update);
    //    } else {
    //        throw new RuntimeException();
    //    }
    //}

    // DELETE: deletes supplier in DB
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable long id) {
        this.guestsRepository.deleteById(id);
    }
}
