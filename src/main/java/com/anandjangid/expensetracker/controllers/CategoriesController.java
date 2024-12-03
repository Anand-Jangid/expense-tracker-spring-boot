package com.anandjangid.expensetracker.controllers;


import com.anandjangid.expensetracker.dtos.categories.CategoriesRequestDto;
import com.anandjangid.expensetracker.dtos.categories.CategoriesResponseDto;
import com.anandjangid.expensetracker.services.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;
    public CategoriesController(final CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping
    public ResponseEntity<CategoriesResponseDto> createCategories(@Valid @RequestBody CategoriesRequestDto categoriesRequestDto) {
        var categoryResponse = categoriesService.createCategories(categoriesRequestDto);
        return ResponseEntity.ok().body(categoryResponse);
    }
}
