package com.anandjangid.expensetracker.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String details) {
}
