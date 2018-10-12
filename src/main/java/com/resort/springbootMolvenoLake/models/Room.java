package com.resort.springbootMolvenoLake.models;



import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;



@Entity

public class Room {

    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    private long number;

    private boolean isAvailable;

    private double price;

    private RoomType typeRoom;



    public enum RoomType {STANDARD("Standard"), DELUXE("Deluxe"), FAMILY("Family");
        RoomType(String name)
        {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }
    }



    public Room()

    {



    }



    public long getId() {

        return id;

    }



    public void setId(long id) {

        this.id = id;

    }



    public long getNumber() {

        return number;

    }



    public void setNumber(long number) {

        this.number = number;

    }



    public boolean isAvailable() {

        return isAvailable;

    }



    public void setAvailable(boolean available) {

        isAvailable = available;

    }



    public double getPrice() {

        return price;

    }



    public void setPrice(double price) {

        this.price = price;

    }



    public RoomType getTypeRoom() {

        return typeRoom;

    }



    public void setTypeRoom(RoomType typeRoom) {

        this.typeRoom = typeRoom;

    }

}

