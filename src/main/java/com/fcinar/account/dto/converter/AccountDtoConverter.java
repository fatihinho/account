package com.fcinar.account.dto.converter;

import com.fcinar.account.dto.AccountDto;
import com.fcinar.account.model.Account;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Collectors;

public class AccountDtoConverter {
    public final CustomerDtoConverter customerDtoConverter;
    public final TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto convert(@NotNull Account from) {
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convert(Objects.requireNonNull(from.getCustomer())),
                Objects.requireNonNull(from.getTransactions()).stream().map(transactionDtoConverter::convert).collect(Collectors.toSet()));
    }
}
