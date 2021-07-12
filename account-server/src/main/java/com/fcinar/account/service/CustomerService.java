package com.fcinar.account.service;

import com.fcinar.account.dto.AccountDto;
import com.fcinar.account.dto.CreateAccountRequest;
import com.fcinar.account.dto.CreateCustomerRequest;
import com.fcinar.account.dto.CustomerDto;
import com.fcinar.account.dto.converter.CustomerDtoConverter;
import com.fcinar.account.exception.CustomerNotFoundException;
import com.fcinar.account.model.Account;
import com.fcinar.account.model.Customer;
import com.fcinar.account.model.Transaction;
import com.fcinar.account.repository.ICustomerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final ICustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(ICustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public CustomerDto createCustomer(@NotNull CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer(
                createCustomerRequest.getName(),
                createCustomerRequest.getSurname()
        );

        return customerDtoConverter.convert(customerRepository.save(customer));
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer could not find by id : " + id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return customerDtoConverter.convert(findCustomerById(customerId));
    }

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerDtoConverter::convert).collect(Collectors.toList());
    }
}
