package com.fcinar.account.dto.converter;

import com.fcinar.account.dto.CustomerAccountDto;
import com.fcinar.account.dto.CustomerDto;
import com.fcinar.account.model.Customer;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerDtoConverter {
    public final CustomerAccountDtoConverter customerAccountDtoConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter converter) {
        this.customerAccountDtoConverter = converter;
    }

    public CustomerDto convert(@NotNull Customer from) {
        return new CustomerDto(from.getId(), from.getName(), from.getSurname(), Objects.requireNonNull(from.getAccounts()).stream().map(customerAccountDtoConverter::convert).collect(Collectors.toSet()));
    }
}
