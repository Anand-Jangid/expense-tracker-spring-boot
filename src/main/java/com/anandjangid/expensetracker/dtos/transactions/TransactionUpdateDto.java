package com.anandjangid.expensetracker.dtos.transactions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionUpdateDto {
    private String type;
    private UUID categoryId;
    private Double amount;
    private LocalDateTime date;
    private String description;
}
