package com.fcinar.account;

import com.fcinar.account.model.Customer;
import com.fcinar.account.repository.ICustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    private final ICustomerRepository customerRepository;

    public AccountApplication(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = customerRepository.save(new Customer("Fatih", "Çınar"));
        System.out.println(customer);
    }
}
