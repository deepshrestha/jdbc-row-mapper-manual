package com.cibt.springapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBook {
    
    private int id;
    private String country;
    private String city;
    private String streetAddress;
    private String email;
    private String phoneNumber;

    public AddressBook() {
    }

    public AddressBook(int id, String country, String city, String streetAddress, String email, String phoneNumber) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.streetAddress = streetAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /* public void setId(int id) {
        this.id = id;
    } */

    // Builder Pattern
    public AddressBook setId(int id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    /* public void setCity(String city) {
        this.city = city;
    } */

    // Builder Pattern
    public AddressBook setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    /* public void setCountry(String country) {
        this.country = country;
    } */

    // Builder Pattern
    public AddressBook setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getEmail() {
        return email;
    }

    /* public void setEmail(String email) {
        this.email = email;
    } */

    // Builder Pattern
    public AddressBook setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /* public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    } */

    // Builder Pattern
    public AddressBook setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    /* public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    } */

    // Builder Pattern
    public AddressBook setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public String toJson() {
        return "{\"id\": " + id + ", \"street_address\": \"" + streetAddress + "\", \"city\": \"" +city + "\", \"phone_number\":  \"" + phoneNumber +"}";
    }
}