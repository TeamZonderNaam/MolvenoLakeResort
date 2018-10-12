package com.resort.springbootMolvenoLake.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", "Order");
        return "data/order/index";
    }
}