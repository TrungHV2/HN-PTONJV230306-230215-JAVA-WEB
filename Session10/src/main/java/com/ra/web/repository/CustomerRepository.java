package com.ra.web.repository;

import com.ra.web.model.Customer;

import java.util.List;


public interface CustomerRepository {
    List<Customer> findAll();
    Customer findId(String id);
    void add(Customer cus);
    void edit(Customer cus);
}
