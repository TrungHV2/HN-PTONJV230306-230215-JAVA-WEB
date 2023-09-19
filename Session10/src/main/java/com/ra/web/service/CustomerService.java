package com.ra.web.service;

import com.ra.web.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findId(String id);
}
