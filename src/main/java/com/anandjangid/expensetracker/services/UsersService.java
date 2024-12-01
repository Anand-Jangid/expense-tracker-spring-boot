package com.anandjangid.expensetracker.services;

import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

//    private Users createUser(String username, String password) {
//
//    }
}
