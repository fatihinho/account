package com.fcinar.account.repository;

import com.fcinar.account.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, String> {
}
