package com.anandjangid.expensetracker.controllers;

import com.anandjangid.expensetracker.dtos.transactions.TransactionRequestDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionResponseDto;
import com.anandjangid.expensetracker.services.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<TransactionResponseDto> getAllTransactions(@RequestParam UUID userId, @RequestParam UUID transactionId) {
        return transactionsService.getAllTransactionsByUserId(userId);
    }

    @GetMapping("/id")
    public TransactionResponseDto getTransactionById(@RequestParam UUID transactionId) {
        return transactionsService.getTransactionById(transactionId);
    }
}
