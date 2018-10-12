package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
}