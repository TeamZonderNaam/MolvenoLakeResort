package com.resort.springbootMolvenoLake.models;

import java.time.LocalDateTime;

public class RestaurantReservation extends Reservation
{
    public RestaurantReservation(LocalDateTime start, LocalDateTime end) {
        super(start, end);
    }
}