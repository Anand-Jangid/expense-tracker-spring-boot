package com.anandjangid.expensetracker.dtos.categories;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoriesResponseDto {
    private UUID id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
}
