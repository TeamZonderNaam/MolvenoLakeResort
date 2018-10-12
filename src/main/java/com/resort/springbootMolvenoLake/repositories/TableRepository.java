package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends CrudRepository<Table, Long> {
}
