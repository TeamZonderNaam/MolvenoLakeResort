package com.resort.springbootMolvenoLake.services;

import com.resort.springbootMolvenoLake.models.Guest;
import com.resort.springbootMolvenoLake.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository repository;

    public long create(Guest guest) {
        Guest created = repository.save(guest);
        return created.getId();
    }

    public List<Guest> all() {
        Iterable<Guest> source = this.repository.findAll();
        List<Guest> target = new ArrayList<>();
        source.forEach(target::add);
        return target;
    }

    public Optional<Guest> read(final long id) {
        Optional<Guest> guest = repository.findById(id);
        return guest;
    }

    public Guest update(Guest guest) {
        return this.repository.save(guest);
    }

    public void delete(final long id) {
        this.repository.deleteById(id);
    }

    public Guest read(final String name) {
        Optional<Guest> guest = repository.findByName(name);
        if (guest.isPresent()) {
            return guest.get();
        }

        return null;
    }
}
