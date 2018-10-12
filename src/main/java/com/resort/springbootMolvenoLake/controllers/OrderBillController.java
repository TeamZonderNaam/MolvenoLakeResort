package com.resort.springbootMolvenoLake.controllers;


import com.resort.springbootMolvenoLake.models.Guest;
import com.resort.springbootMolvenoLake.models.OrderBill;
import com.resort.springbootMolvenoLake.models.Supplier;
import com.resort.springbootMolvenoLake.repositories.GuestRepository;
import com.resort.springbootMolvenoLake.repositories.OrderBillRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orderbill/")
public class OrderBillController {

    @Autowired
    private OrderBillRepository orderBillRepository;

    @Autowired
    private GuestRepository guestRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<OrderBill> getAll() {
        return orderBillRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public OrderBill create(@RequestBody OrderBill orderBill) {
        return orderBillRepository.save(orderBill);
    }

    // UPDATE: updates supplier in DB
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public OrderBill updateById(@PathVariable long id, @RequestBody OrderBill orderBill) {
        if(orderBillRepository.existsById(id)){
            orderBill.setId(id);
            return orderBillRepository.save(orderBill);
        } else {
            throw new RuntimeException();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable long id) {
        this.orderBillRepository.deleteById(id);
    }



    // GET all orders for Guest x
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Iterable<OrderBill> findOrderByGuest(@PathVariable long id) {
        if(guestRepository.existsById(id)) {
            Guest guest = guestRepository.findById(id).get();
            Iterable<OrderBill> bills = orderBillRepository.findAllByGuest(guest);
            for (OrderBill order: bills) {
                order.setTotal(order.getPrice() * order.getQuantity());
            }
            return bills;
        }
        else {
            throw new RuntimeException();
        }
    }
}
