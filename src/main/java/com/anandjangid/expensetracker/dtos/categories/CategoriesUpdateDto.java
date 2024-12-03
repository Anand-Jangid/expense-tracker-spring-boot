package com.anandjangid.expensetracker.dtos.categories;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoriesUpdateDto {
    private String name;
    private String description;
}
