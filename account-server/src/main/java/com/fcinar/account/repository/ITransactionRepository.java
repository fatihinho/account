package com.fcinar.account.repository;

import com.fcinar.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepository extends JpaRepository<Transaction, String> {
}
