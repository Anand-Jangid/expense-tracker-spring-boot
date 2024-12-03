package com.anandjangid.expensetracker.services;


import com.anandjangid.expensetracker.dtos.categories.CategoriesRequestDto;
import com.anandjangid.expensetracker.dtos.categories.CategoriesResponseDto;
import com.anandjangid.expensetracker.entities.Categories;
import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.repositories.CategoriesRepository;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.stereotype.Service;

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

    private static CategoriesResponseDto getCategoriesResponseDto(Categories savedCategory) {
        CategoriesResponseDto categoriesResponseDto = new CategoriesResponseDto();
        categoriesResponseDto.setName(savedCategory.getName());
        categoriesResponseDto.setDescription(savedCategory.getDescription());
        categoriesResponseDto.setCreatedAt(String.valueOf(savedCategory.getCreatedAt()));
        categoriesResponseDto.setUpdatedAt(String.valueOf(savedCategory.getUpdatedAt()));
        return categoriesResponseDto;
    }
}
