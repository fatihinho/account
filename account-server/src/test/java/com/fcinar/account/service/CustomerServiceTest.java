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
        Customer expected = new Customer("customer-id", "name", "surname", Set.of());
        when(customerRepository.findById("customer-id")).thenReturn(Optional.of(expected));
        Customer actual = service.findCustomerById("customer-id");
        assertEquals(expected, actual);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("id"));

    }

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("customer-id", "name", "surname", Set.of());
        CustomerDto expected = new CustomerDto("customer-id", "name", "surname", Set.of());
        when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
        when(customerDtoConverter.convert(customer)).thenReturn(expected);
        CustomerDto actual = service.getCustomerById("customer-id");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.getCustomerById("id"));
        verifyNoInteractions(customerDtoConverter);
    }
}