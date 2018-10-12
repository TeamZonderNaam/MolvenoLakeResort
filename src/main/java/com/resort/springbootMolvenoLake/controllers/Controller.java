package com.resort.springbootMolvenoLake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class Controller
{
    @Autowired
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }
}
