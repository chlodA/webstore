package com.example.SpringApp.repository;

import com.example.SpringApp.model.Customer;
import com.example.SpringApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //List<Customer> getAllCustomers();

}
