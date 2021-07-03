package com.fcinar.account.service;

import com.fcinar.account.dto.AccountDto;
import com.fcinar.account.dto.CreateAccountRequest;
import com.fcinar.account.dto.converter.AccountDtoConverter;
import com.fcinar.account.model.Account;
import com.fcinar.account.model.Customer;
import com.fcinar.account.model.Transaction;
import com.fcinar.account.repository.IAccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final IAccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;

    public AccountService(IAccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(@NotNull CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());
        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now()
        );

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(
                    createAccountRequest.getInitialCredit(),
                    LocalDateTime.now(),
                    account);

            account.getTransactions().add(transaction);
        }

        return converter.convert(accountRepository.save(account));
    }
}
