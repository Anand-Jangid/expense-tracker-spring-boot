package com.anandjangid.expensetracker.controllers;

import com.anandjangid.expensetracker.dtos.transactions.TransactionRequestDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionResponseDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionUpdateDto;
import com.anandjangid.expensetracker.services.TransactionsService;
import jakarta.servlet.http.HttpServletRequest;
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
    public TransactionResponseDto createTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto, HttpServletRequest httpServletRequest) {
        UUID userId = UUID.fromString((String) httpServletRequest.getAttribute("userId"));
        return transactionsService.createTransaction(transactionRequestDto, userId);
    }

    @GetMapping
    public List<TransactionResponseDto> getAllTransactions(HttpServletRequest httpServletRequest) {
        UUID userId = UUID.fromString((String) httpServletRequest.getAttribute("userId"));
        return transactionsService.getAllTransactionsByUserId(userId);
    }

    //TODO: Make sure that the id of transaction is of the user
    @GetMapping("/{id}")
    public TransactionResponseDto getTransactionById(@PathVariable UUID id) {
        return transactionsService.getTransactionById(id);
    }

    //TODO: Make sure that the id of transaction is of the user
    @DeleteMapping("/{id}")
    public void deleteTransactionById(@PathVariable UUID id){
        transactionsService.deleteTransactionById(id);
    }

    //TODO: Make sure that the id of transaction is of the user
    @PatchMapping("/{id}")
    public TransactionResponseDto updateTransaction(@Valid @RequestBody TransactionUpdateDto transactionUpdateDto, @PathVariable UUID id){
        return transactionsService.updateTransaction(id, transactionUpdateDto);
    }
}
