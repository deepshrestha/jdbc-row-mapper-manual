package com.cibt.springapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    
    private int id;
    private String customerName;
    private AddressBook addressBook;

    public Customer() {
    }

    public Customer(int id, String customerName, AddressBook addressBook) {
        this.id = id;
        this.customerName = customerName;
        this.addressBook = addressBook;
    }

    public int getId() {
        return id;
    }

    public Customer setId(int id) {
        this.id = id;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Customer setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public Customer setAddressBook(AddressBook address) {
        this.addressBook = address;
        return this;
    }

    public String toJson() {
        return "{\"id\":"+id+",\"customer_name\":\""+customerName+"\",\"address\":" + addressBook.toJson()+"}";
    }

}