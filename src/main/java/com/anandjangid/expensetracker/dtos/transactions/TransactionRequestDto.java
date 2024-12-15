package com.anandjangid.expensetracker.dtos.transactions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionRequestDto {

    @NotBlank(message = "Transaction type cannot be null or empty")
    private String transactionType;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Double amount;

    private String description;

    @NotNull(message = "Category ID cannot be null")
    private UUID categoryId;
}

