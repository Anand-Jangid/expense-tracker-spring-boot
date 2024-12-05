package com.anandjangid.expensetracker.dtos.transactions;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransactionResponseDto {
    private UUID id;
    private String transactionType;
    private String description;
    private Double amount;
    private String createdAt;
    private String updatedAt;
}
