package com.anandjangid.expensetracker.services;

import com.anandjangid.expensetracker.dtos.users.UserRequestDto;
import com.anandjangid.expensetracker.dtos.users.UserResponseDto;
import com.anandjangid.expensetracker.dtos.users.UserUpdateDto;
import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // Map UserRequestDto to Users entity
        Users user = new Users();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());

        // Save the user to the database
        Users savedUser = usersRepository.save(user);

        // Map Users entity to UserResponseDto
        return getUserResponseDto(savedUser);
    }

    public UserResponseDto getUserById(UUID id) {
        Users user = usersRepository.findById(id).orElse(null);
        if(user == null) {
            return null;
        }
        return getUserResponseDto(user);
    }

    public UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
        Users user = usersRepository.findById(id).orElse(null);
        if(user == null) {
            return null;
        }
        if(userUpdateDto.getName() != null) {
            user.setName(userUpdateDto.getName());
        }
        if(userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if(userUpdateDto.getPassword() != null) {
            user.setPassword(userUpdateDto.getPassword());
        }
        Users savedUser = usersRepository.save(user);
        return getUserResponseDto(savedUser);
    }

    private UserResponseDto getUserResponseDto(Users user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setEmail(user.getEmail());
        responseDto.setCreatedAt(user.getCreatedAt().toString());
        responseDto.setUpdatedAt(user.getUpdatedAt().toString());
        return responseDto;
    }
}
