package com.fcinar.account.service;

import com.fcinar.account.exception.CustomerNotFoundException;
import com.fcinar.account.model.Customer;
import com.fcinar.account.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected Customer findCustomerById(String id) {
        return this.customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer could not find by id : " + id));
    }
}
