package com.fcinar.account.dto.converter;

import com.fcinar.account.dto.TransactionDto;
import com.fcinar.account.model.Transaction;
import org.jetbrains.annotations.NotNull;

public class TransactionDtoConverter {
    public final AccountDtoConverter accountDtoConverter;

    public TransactionDtoConverter(AccountDtoConverter accountDtoConverter) {
        this.accountDtoConverter = accountDtoConverter;
    }

    public TransactionDto convert(@NotNull Transaction from) {
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate(),
                accountDtoConverter.convert(from.getAccount()));
    }
}
