package com.resort.springbootMolvenoLake.controllers.api;

import com.resort.springbootMolvenoLake.models.MenuItem;
import com.resort.springbootMolvenoLake.models.Order;
import com.resort.springbootMolvenoLake.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController("order_api")
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Order> get() {
        return this.orderService.all();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getSingle(@PathVariable int id) {
        return this.orderService.read(id);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order add(@RequestBody Order order) {
        this.orderService.create(order);
        return order;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order update(@PathVariable int id, @RequestBody Order order) {
        return this.orderService.update(id, order);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable int id) {
        this.orderService.delete(id);
        return "{}";
    }


    @PostMapping(value = "/add/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addToOrder(@PathVariable("id") int id, @RequestBody MenuItem item) {
        orderService.addMenuItem(id, item);
        System.out.println("Got Order");
        return "{}";
    }
}
