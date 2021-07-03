package com.fcinar.account.dto.converter;

import com.fcinar.account.dto.TransactionDto;
import com.fcinar.account.model.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    public TransactionDto convert(@NotNull Transaction from) {
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
