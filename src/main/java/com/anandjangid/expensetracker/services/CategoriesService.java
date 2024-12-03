package com.anandjangid.expensetracker.services;


import com.anandjangid.expensetracker.dtos.categories.CategoriesRequestDto;
import com.anandjangid.expensetracker.dtos.categories.CategoriesResponseDto;
import com.anandjangid.expensetracker.dtos.categories.CategoriesUpdateDto;
import com.anandjangid.expensetracker.entities.Categories;
import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.exceptions.categories.CategoryNotFoundException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.repositories.CategoriesRepository;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final UsersRepository usersRepository;
    public CategoriesService(CategoriesRepository categoriesRepository, UsersRepository usersRepository) {
        this.categoriesRepository = categoriesRepository;
        this.usersRepository = usersRepository;
    }

    public CategoriesResponseDto createCategories(CategoriesRequestDto categoriesRequestDto) {
        Users user = usersRepository.findById(categoriesRequestDto.getUserId()).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("user id: " + categoriesRequestDto.getUserId() + " not found");
        }

        Categories categories = new Categories();

        categories.setName(categoriesRequestDto.getName());
        categories.setDescription(categoriesRequestDto.getDescription());
        categories.setUser(user);
        var savedCategory = categoriesRepository.save(categories);
        return getCategoriesResponseDto(savedCategory);
    }

    public List<CategoriesResponseDto> getAllCategoriesByUserId(UUID userId) {
        List<Categories> categoriesList = categoriesRepository.findByUserId(userId);
        List<CategoriesResponseDto> categoriesResponseDtoList = new ArrayList<>();
        categoriesList.forEach(categories -> {
            categoriesResponseDtoList.add(getCategoriesResponseDto(categories));
        });
        return categoriesResponseDtoList;
    }

    public CategoriesResponseDto updateCategoriesById(UUID categoryId, CategoriesUpdateDto categoriesUpdateDto) {
        Categories categories = categoriesRepository.findById(categoryId).orElse(null);
        if(categories == null) {
            throw new CategoryNotFoundException("category id: " + categoryId + "not found");
        }
        if(categoriesUpdateDto.getName() != null) {
            categories.setName(categoriesUpdateDto.getName());
        }
        if(categoriesUpdateDto.getDescription() != null) {
            categories.setDescription(categoriesUpdateDto.getDescription());
        }
        var savedCategory = categoriesRepository.save(categories);
        return getCategoriesResponseDto(savedCategory);
    }

    public void deleteCategoriesById(UUID categoryId) {
        Categories categories = categoriesRepository.findById(categoryId).orElse(null);
        if(categories == null) {
            throw new CategoryNotFoundException("category id: " + categoryId + "not found");
        }
        categoriesRepository.delete(categories);
    }

    private static CategoriesResponseDto getCategoriesResponseDto(Categories savedCategory) {
        CategoriesResponseDto categoriesResponseDto = new CategoriesResponseDto();
        categoriesResponseDto.setId(savedCategory.getId());
        categoriesResponseDto.setName(savedCategory.getName());
        categoriesResponseDto.setDescription(savedCategory.getDescription());
        categoriesResponseDto.setCreatedAt(String.valueOf(savedCategory.getCreatedAt()));
        categoriesResponseDto.setUpdatedAt(String.valueOf(savedCategory.getUpdatedAt()));
        return categoriesResponseDto;
    }
}
