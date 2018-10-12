package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
    Optional<Guest> findByName(String name);
}