package com.example.SpringApp.service;

import com.example.SpringApp.model.Customer;
import com.example.SpringApp.model.Product;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();
    void addNewCustomer(Customer customer);
}
