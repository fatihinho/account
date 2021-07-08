package com.fcinar.account.repository;

import com.fcinar.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, String> {
}
