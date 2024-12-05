package com.anandjangid.expensetracker.controllers;

import com.anandjangid.expensetracker.dtos.transactions.TransactionRequestDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionResponseDto;
import com.anandjangid.expensetracker.services.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping
    public TransactionResponseDto createTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        return transactionsService.createTransaction(transactionRequestDto);
    }
}
