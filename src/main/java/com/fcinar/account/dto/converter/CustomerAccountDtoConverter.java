package com.fcinar.account.dto.converter;

import com.fcinar.account.dto.CustomerAccountDto;
import com.fcinar.account.model.Account;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerAccountDtoConverter {
    public final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public CustomerAccountDto convert(@NotNull Account from) {
        return new CustomerAccountDto(Objects.requireNonNull(from.getId()),
                from.getBalance(),
                Objects.requireNonNull(from.getTransactions()).stream().map(transactionDtoConverter::convert).collect(Collectors.toSet()),
                from.getCreationDate());
    }
}
