package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    // Custom JPA query
    // TODO: Revert in a bit
//    public List<Reservation> findAllByDate(LocalDate date);
}
