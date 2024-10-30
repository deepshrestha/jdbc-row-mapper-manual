package com.cibt.springapp.dao;

import java.util.List;

import com.cibt.springapp.models.AddressBook;
import com.cibt.springapp.models.Customer;

public interface CustomerAddressBookDAO {
    int insert(Customer customer);
    int update(Customer customer);
    int delete(int id);
    List<Customer> fetchAll();
    int insert(AddressBook addressBook);
    int deleteAddressBook(int id);
}