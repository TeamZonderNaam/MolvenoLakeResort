package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

}