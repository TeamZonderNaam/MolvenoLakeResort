package com.resort.springbootMolvenoLake.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class OrderBill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String product;
    private int quantity;
    private double price;
    private double total;
    private String timeOfOrder;
    private boolean paid;

    @ManyToOne
    @NotNull
    private Guest guest;

    // methods
    public void saveOrderInDatabase(String product, int quantity, double price, boolean paid) {

    }

    // constructor
    public OrderBill() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(String timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
