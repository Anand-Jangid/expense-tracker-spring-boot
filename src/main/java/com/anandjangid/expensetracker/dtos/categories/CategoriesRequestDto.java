package com.anandjangid.expensetracker.dtos.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CategoriesRequestDto {
    @NotBlank(message = "name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @Size(max = 255, message = "Name must be less than 255 characters")
    private String description;
}
