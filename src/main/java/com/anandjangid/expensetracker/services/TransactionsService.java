package com.anandjangid.expensetracker.services;

import com.anandjangid.expensetracker.dtos.transactions.TransactionRequestDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionResponseDto;
import com.anandjangid.expensetracker.entities.Categories;
import com.anandjangid.expensetracker.entities.Transactions;
import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.exceptions.categories.CategoryNotFoundException;
import com.anandjangid.expensetracker.exceptions.transactions.TransactionNotFoundException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.repositories.CategoriesRepository;
import com.anandjangid.expensetracker.repositories.TransactionsRepository;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Transactions savedTransaction = transactionsRepository.save(transactions);

        return getTransactionResponseDto(savedTransaction);
    }

    private static TransactionResponseDto getTransactionResponseDto(Transactions savedTransaction) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setId(savedTransaction.getId());
        transactionResponseDto.setAmount(savedTransaction.getAmount());
        transactionResponseDto.setTransactionType(savedTransaction.getTransactionType());
        transactionResponseDto.setDescription(savedTransaction.getDescription());
        transactionResponseDto.setCreatedAt(savedTransaction.getCreatedAt().toString());
        transactionResponseDto.setUpdatedAt(savedTransaction.getUpdatedAt().toString());
        return transactionResponseDto;
    }

    public List<TransactionResponseDto> getAllTransactionsByUserId(UUID userId) {
        if(userId == null) return null;
        Users user = usersRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new UserNotFoundException("User id: "+ userId+ " not found");
        }
        List<Transactions> transactions = transactionsRepository.findByUser(user);
        List<TransactionResponseDto> transactionResponseDtos = new ArrayList<>();
        transactions.forEach((transaction) -> {
            transactionResponseDtos.add(getTransactionResponseDto(transaction));
        });

        return transactionResponseDtos;
    }

    public TransactionResponseDto getTransactionById(UUID transactionId){
        Transactions transactions = transactionsRepository.findById(transactionId).orElse(null);
        if(transactions == null){
            throw new TransactionNotFoundException("Transaction id: "+ transactionId+ " not found");
        }
        return getTransactionResponseDto(transactions);
    }

    public void deleteTransactionById(UUID transactionId){
        Transactions transactions = transactionsRepository.findById(transactionId).orElse(null);
        if(transactions == null){
            throw new TransactionNotFoundException("Transaction id: " + transactionId + " not found");
        }

        transactionsRepository.deleteById(transactionId);
    }
}
