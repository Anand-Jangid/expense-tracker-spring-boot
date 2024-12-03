package com.anandjangid.expensetracker.dtos.categories;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class CategoriesUpdateDto {
    private UUID id;
    private String name;
    private String description;
}
