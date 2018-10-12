package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
