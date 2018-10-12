package com.resort.springbootMolvenoLake.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class HotelReservation extends Reservation
{
    public static final String START_TIME = "@14:00";
    public static final String END_TIME = "@11:00";

    public HotelReservation(){}
    public HotelReservation(LocalDateTime start, LocalDateTime end, Room room, Guest guest)
    {
        super(start, end);
        this.room = room;
        this.guest = guest;
    }
    @ManyToOne
    private Room room;
    @ManyToOne
    private Guest guest;

    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }


}
