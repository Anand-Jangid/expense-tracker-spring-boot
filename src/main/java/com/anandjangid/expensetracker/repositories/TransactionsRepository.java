package com.anandjangid.expensetracker.repositories;

import com.anandjangid.expensetracker.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionsRepository extends JpaRepository<Transactions, UUID> {
}
