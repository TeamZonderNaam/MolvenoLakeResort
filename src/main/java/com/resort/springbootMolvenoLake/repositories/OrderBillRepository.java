package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Guest;
import com.resort.springbootMolvenoLake.models.OrderBill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBillRepository extends CrudRepository<OrderBill, Long> {
    Iterable<OrderBill> findAllByGuest(Guest guest);
}
