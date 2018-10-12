package com.resort.springbootMolvenoLake.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String firstName;
    private String lastName;
    private String streetName;
    private int houseNumber;
    private String additionHouseNumber = "";
    private String postcode;
    private String country;
    private String stateOrProvince;
    private int creditCardDetails;
    private int numberOfAdults;
    private int numberOfChildren; // not necessary
    private boolean babyBed = false; // not necessary
    private boolean bringsDog = false; // not necessary

    public Guest(){}

    public String getName() {
        if (this.name == null) {
            if (this.firstName != null && this.lastName != null) {
                name = this.firstName + " " + this.lastName;
            }
            else if (this.lastName != null) {
                name = this.lastName;
            }
            else if (this.firstName != null) {
                name = this.firstName;
            }
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.name = getName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.name = getName();
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAdditionHouseNumber() {
        return additionHouseNumber;
    }

    public void setAdditionHouseNumber(String additionHouseNumber) {
        this.additionHouseNumber = additionHouseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public boolean isBabyBed() {
        return babyBed;
    }

    public void setBabyBed(boolean babyBed) {
        this.babyBed = babyBed;
    }

    public boolean isBringsDog() {
        return bringsDog;
    }

    public void setBringsDog(boolean bringsDog) {
        this.bringsDog = bringsDog;
    }

    public int getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(int creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }


    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
