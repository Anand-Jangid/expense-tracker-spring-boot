package com.anandjangid.expensetracker.services;

import com.anandjangid.expensetracker.dtos.transactions.TransactionRequestDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionResponseDto;
import com.anandjangid.expensetracker.entities.Categories;
import com.anandjangid.expensetracker.entities.Transactions;
import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.exceptions.categories.CategoryNotFoundException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.repositories.CategoriesRepository;
import com.anandjangid.expensetracker.repositories.TransactionsRepository;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    private TransactionsRepository transactionsRepository;
    private UsersRepository usersRepository;
    private CategoriesRepository categoriesRepository;

    public TransactionsService(TransactionsRepository transactionsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository) {
        this.transactionsRepository = transactionsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        Users users = usersRepository.findById(transactionRequestDto.getUserId()).orElse(null);
        if(users == null) {
            throw new UserNotFoundException("User id: "+ transactionRequestDto.getUserId()+ " not found");
        }
        Categories categories = categoriesRepository.findById(transactionRequestDto.getCategoryId()).orElse(null);
        if(categories == null){
            throw new CategoryNotFoundException("Category id: "+ transactionRequestDto.getCategoryId()+ " not found");
        }
        Transactions transactions = new Transactions();
        transactions.setTransactionType(transactionRequestDto.getTransactionType());
        transactions.setAmount(transactionRequestDto.getAmount());
        transactions.setDescription(transactionRequestDto.getDescription());
        transactions.setCategory(categories);
        transactions.setUser(users);
        transactionsRepository.save(transactions);

        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setId(transactions.getId());
        transactionResponseDto.setAmount(transactions.getAmount());
        transactionResponseDto.setTransactionType(transactions.getTransactionType());
        transactionResponseDto.setDescription(transactions.getDescription());
        transactionResponseDto.setCreatedAt(transactions.getCreatedAt().toString());
        transactionResponseDto.setUpdatedAt(transactions.getUpdatedAt().toString());
        return transactionResponseDto;
    }
}
