package com.fcinar.account.service;

import com.fcinar.account.dto.CustomerDto;
import com.fcinar.account.dto.converter.CustomerDtoConverter;
import com.fcinar.account.exception.CustomerNotFoundException;
import com.fcinar.account.model.Customer;
import com.fcinar.account.repository.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    private ICustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;
    private CustomerService service;

    @BeforeEach
    public void setup() {
        customerRepository = mock(ICustomerRepository.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository, customerDtoConverter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("customer-id", "name", "surname", Set.of());
        when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
        Customer result = service.findCustomerById("customer-id");
        assertEquals(result, customer);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("id"));

    }

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("customer-id", "name", "surname", Set.of());
        CustomerDto customerDto = new CustomerDto("customer-id", "name", "surname", Set.of());
        when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
        when(customerDtoConverter.convert(customer)).thenReturn(customerDto);
        CustomerDto result = service.getCustomerById("customer-id");
        assertEquals(result, customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.getCustomerById("id"));
        verifyNoInteractions(customerDtoConverter);
    }
}