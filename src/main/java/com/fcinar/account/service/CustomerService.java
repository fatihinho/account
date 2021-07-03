package com.fcinar.account.service;

import com.fcinar.account.dto.CustomerDto;
import com.fcinar.account.dto.converter.CustomerDtoConverter;
import com.fcinar.account.exception.CustomerNotFoundException;
import com.fcinar.account.model.Customer;
import com.fcinar.account.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final ICustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(ICustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer could not find by id : " + id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return customerDtoConverter.convert(findCustomerById(customerId));
    }
}
