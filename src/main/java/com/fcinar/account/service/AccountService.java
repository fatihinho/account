package com.fcinar.account.service;

import com.fcinar.account.repository.IAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public final IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
