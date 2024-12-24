package com.anandjangid.expensetracker.repositories;

import com.anandjangid.expensetracker.entities.Transactions;
import com.anandjangid.expensetracker.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionsRepository extends JpaRepository<Transactions, UUID> {
    List<Transactions> findByUserOrderByCreatedAtDesc(Users user);
}
