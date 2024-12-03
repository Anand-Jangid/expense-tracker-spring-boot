package com.anandjangid.expensetracker.repositories;

import com.anandjangid.expensetracker.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Categories, UUID> {
    List<Categories> findByUserId(UUID userId);
}
