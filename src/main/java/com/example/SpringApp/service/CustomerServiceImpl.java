package com.example.SpringApp.service;

import com.example.SpringApp.model.Customer;
import com.example.SpringApp.model.Product;
import com.example.SpringApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void addNewCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }
}
