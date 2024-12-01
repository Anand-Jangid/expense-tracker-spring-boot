package com.anandjangid.expensetracker.repositories;

import com.anandjangid.expensetracker.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Categories, UUID> {
}
