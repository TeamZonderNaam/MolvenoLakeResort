package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends CrudRepository<Facility, Long> {
}
