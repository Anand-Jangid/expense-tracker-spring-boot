package com.anandjangid.expensetracker.services;

import com.anandjangid.expensetracker.dtos.transactions.TransactionRequestDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionResponseDto;
import com.anandjangid.expensetracker.dtos.transactions.TransactionUpdateDto;
import com.anandjangid.expensetracker.entities.Categories;
import com.anandjangid.expensetracker.entities.Transactions;
import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.exceptions.categories.CategoryNotFoundException;
import com.anandjangid.expensetracker.exceptions.transactions.TransactionNotFoundException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.repositories.CategoriesRepository;
import com.anandjangid.expensetracker.repositories.TransactionsRepository;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;

    public TransactionsService(TransactionsRepository transactionsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository) {
        this.transactionsRepository = transactionsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Transactional
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto, UUID userId) {
        Users users = usersRepository.findById(userId).orElse(null);
        if(users == null) {
            throw new UserNotFoundException("User id: "+ userId+ " not found");
        }
        //TODO: Make sure user does not takes categoryId of some other user
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
        List<Transactions> transactions = transactionsRepository.findByUserOrderByCreatedAtDesc(user);
        List<TransactionResponseDto> transactionResponseDtos = new ArrayList<>();
        transactions.forEach((transaction) -> transactionResponseDtos.add(getTransactionResponseDto(transaction)));

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

    public TransactionResponseDto updateTransaction(UUID transactionId, TransactionUpdateDto transactionUpdateDto){
        Transactions transactions = transactionsRepository.findById(transactionId).orElse(null);
        if(transactions == null){
            throw new TransactionNotFoundException("Transactions id: " + transactionId + " not found");
        }

        if(transactionUpdateDto.getCategoryId() != null){
            Categories categories = categoriesRepository.findById(transactionUpdateDto.getCategoryId()).orElse(null);
            if(categories == null){
                throw new CategoryNotFoundException("Category id: " + transactionUpdateDto.getCategoryId() + " not found.");
            }
            transactions.setCategory(categories);
        }
        if(transactionUpdateDto.getType() != null){
            transactions.setTransactionType(transactionUpdateDto.getType());
        }
        if(transactionUpdateDto.getDescription() != null){
            transactions.setDescription(transactionUpdateDto.getDescription());
        }
        if(transactionUpdateDto.getAmount() != null){
            transactions.setAmount(transactionUpdateDto.getAmount());
        }
        Transactions updatedTransaction = transactionsRepository.save(transactions);

        return getTransactionResponseDto(updatedTransaction);
    }
}
