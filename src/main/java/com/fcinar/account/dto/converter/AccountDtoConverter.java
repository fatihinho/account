package com.fcinar.account.dto.converter;

import com.fcinar.account.dto.AccountDto;
import com.fcinar.account.model.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto convert(@NotNull Account from) {
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccountCustomerDto(Objects.requireNonNull(from.getCustomer())),
                Objects.requireNonNull(from.getTransactions()).stream().map(transactionDtoConverter::convert).collect(Collectors.toSet()));
    }
}
