package com.anandjangid.expensetracker.dtos.categories;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriesResponseDto {
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
}
