package com.anandjangid.expensetracker.services;

import com.anandjangid.expensetracker.dtos.users.UserLoginDto;
import com.anandjangid.expensetracker.dtos.users.UserRequestDto;
import com.anandjangid.expensetracker.dtos.users.UserResponseDto;
import com.anandjangid.expensetracker.dtos.users.UserUpdateDto;
import com.anandjangid.expensetracker.entities.Users;
import com.anandjangid.expensetracker.exceptions.users.UserAlreadyExistsException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.jwt.JWTUtil;
import com.anandjangid.expensetracker.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final JWTUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(UserRequestDto userRequestDto) throws UserAlreadyExistsException {
        var user = createUser(userRequestDto);
        return jwtUtil.generateToken(user.getId());
    }

    public String loginUser(UserLoginDto userLoginDto) throws UserNotFoundException {
        var user = getUserByEmail(userLoginDto.getEmail());
        if(passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getId());
        }
        throw new UserNotFoundException("email or password incorrect");
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        Optional<Users> oldUser = usersRepository.findByEmail(userRequestDto.getEmail());

        if(oldUser.isPresent()){
            throw new UserAlreadyExistsException("Email "+ userRequestDto.getEmail() +" already exists");
        }
        // Map UserRequestDto to Users entity
        Users user = new Users();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

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

    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " +email + " not found."));
    }

    public UserResponseDto getUserResponseDto(Users user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setEmail(user.getEmail());
        responseDto.setCreatedAt(user.getCreatedAt().toString());
        responseDto.setUpdatedAt(user.getUpdatedAt().toString());
        return responseDto;
    }
}
