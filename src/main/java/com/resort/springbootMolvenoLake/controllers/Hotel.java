package com.resort.springbootMolvenoLake.controllers;

import com.resort.springbootMolvenoLake.controllers.api.RoomController;
import com.resort.springbootMolvenoLake.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hotel
{
    // Singleton
    private Hotel(){}
    private static Hotel instance;
    public static synchronized Hotel getInstance(){
        if(instance == null)
            instance = new Hotel();
        return instance;
    }

    // Variables
    private String address;
    private String name = "Molveno Lake Resort";

    // Declaring our systems that are located in the Controller
    @Autowired
    private HotelReservationController hotelReservationController;// = new CalendarController();
    @Autowired
    private com.resort.springbootMolvenoLake.controllers.api.RoomController roomController;// = new RoomController();
    @Autowired
    private GuestService guestController;//= new GuestsController();
    @Autowired
    private OrderBillController orderBillController;


    public HotelReservationController getHotelReservationController() {
        return hotelReservationController;
    }

    public RoomController getRoomController() {
        return roomController;
    }

    public GuestService getGuestController() {
        return guestController;
    }

    public OrderBillController getOrderBillController() {
        return orderBillController;
    }
}