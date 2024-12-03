package com.anandjangid.expensetracker.controllers;


import com.anandjangid.expensetracker.dtos.categories.CategoriesRequestDto;
import com.anandjangid.expensetracker.dtos.categories.CategoriesResponseDto;
import com.anandjangid.expensetracker.dtos.categories.CategoriesUpdateDto;
import com.anandjangid.expensetracker.services.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<List<CategoriesResponseDto>> getCategories(@PathVariable UUID id) {
        var categoryList = categoriesService.getAllCategoriesByUserId(id);
        return ResponseEntity.ok().body(categoryList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoriesResponseDto> updateCategoriesById(@PathVariable UUID id, @Valid @RequestBody CategoriesUpdateDto categoriesUpdateDto) {
        var categoryResponse = categoriesService.updateCategoriesById(id, categoriesUpdateDto);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteCategoriesById(@PathVariable UUID id) {
        categoriesService.deleteCategoriesById(id);
        return ResponseEntity.ok().build();
    }

}
