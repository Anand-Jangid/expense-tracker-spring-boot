package com.anandjangid.expensetracker.dtos.group;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupAddUserDto {
    @NotNull(message = "groupId is required and should not be empty")
    private UUID groupId;

    @NotNull(message = "userId is required and should not be empty")
    private UUID userId;
}
