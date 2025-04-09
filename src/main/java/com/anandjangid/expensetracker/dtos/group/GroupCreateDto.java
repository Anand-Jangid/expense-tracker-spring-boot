package com.anandjangid.expensetracker.dtos.group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GroupCreateDto {

    @NotBlank(message = "name is required and should not be empty")
    @Size(max = 255, min = 0, message = "Name must be less than 255 characters")
    private String name;
}
