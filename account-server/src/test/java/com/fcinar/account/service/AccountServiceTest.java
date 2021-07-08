package com.fcinar.account.service;

import com.fcinar.account.dto.AccountCustomerDto;
import com.fcinar.account.dto.AccountDto;
import com.fcinar.account.dto.CreateAccountRequest;
import com.fcinar.account.dto.TransactionDto;
import com.fcinar.account.dto.converter.AccountDtoConverter;
import com.fcinar.account.model.Account;
import com.fcinar.account.model.Customer;
import com.fcinar.account.model.Transaction;
import com.fcinar.account.model.TransactionType;
import com.fcinar.account.repository.IAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    private IAccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter accountDtoConverter;
    private AccountService service;

    private final Customer customer = new Customer(
            "customer-id",
            "customer-name",
            "customer-surname",
            Set.of()
    );

    private final AccountCustomerDto accountCustomerDto = new AccountCustomerDto(
            "customer-id",
            "customer-name",
            "customer-surname"
    );

    @BeforeEach
    public void setup() {
        accountRepository = mock(IAccountRepository.class);
        customerService = mock(CustomerService.class);
        accountDtoConverter = mock(AccountDtoConverter.class);
        service = new AccountService(accountRepository, customerService, accountDtoConverter);
    }

    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditMoreThanZero_shouldCreateAccountWithTransaction() {
        CreateAccountRequest request = new CreateAccountRequest("customer-id", new BigDecimal(100));

        Account account = new Account(customer, new BigDecimal(100), LocalDateTime.now());
        Transaction transaction = new Transaction(
                null,
                TransactionType.INITIAL,
                request.getInitialCredit(),
                LocalDateTime.now(),
                account
        );
        account.getTransactions().add(transaction);

        TransactionDto transactionDto = new TransactionDto(
                null,
                TransactionType.INITIAL,
                new BigDecimal(100),
                LocalDateTime.now()
        );
        AccountDto expected = new AccountDto(
                "account-id",
                new BigDecimal(100),
                LocalDateTime.now(),
                accountCustomerDto,
                Set.of(transactionDto)
        );

        when(customerService.findCustomerById("customer-id")).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountDtoConverter.convert(account)).thenReturn(expected);

        AccountDto actual = service.createAccount(request); // null problem

        assertEquals(expected, actual);
    }
}
