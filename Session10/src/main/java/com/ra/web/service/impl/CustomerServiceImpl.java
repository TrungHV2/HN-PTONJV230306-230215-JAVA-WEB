package com.ra.web.service.impl;

import com.ra.web.model.Customer;
import com.ra.web.repository.CustomerRepository;
import com.ra.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    //@Qualifier("customerRepositoryImpl")
    private CustomerRepository repository;

//    @Autowired
//    public CustomerServiceImpl(CustomerRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findId(String id) {
        return repository.findId(id);
    }
}
