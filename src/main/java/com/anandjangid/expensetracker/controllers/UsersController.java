package com.anandjangid.expensetracker.controllers;

import com.anandjangid.expensetracker.dtos.users.UserRequestDto;
import com.anandjangid.expensetracker.dtos.users.UserResponseDto;
import com.anandjangid.expensetracker.dtos.users.UserUpdateDto;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/users")
@RestController
public class UsersController {
    private final UsersService userService;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto responseDto = userService.createUser(userRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserResponseDto> getUserProfile(@PathVariable UUID id) {
        UserResponseDto responseDto = userService.getUserById(id);
        if(responseDto == null) {
            throw new UserNotFoundException("user with id: " + id + " not found");
        }
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/profile/{id}")
    public ResponseEntity<UserResponseDto> updateUserProfile(@RequestBody UserUpdateDto userUpdateDto, @PathVariable UUID id) {
        if(id == null) throw new UserNotFoundException("user with id: " + id + " not found");
        UserResponseDto userResponseDto = userService.updateUser(id, userUpdateDto);
        if(userResponseDto == null) throw new UserNotFoundException("user with id: " + id + " not found");
        return ResponseEntity.ok(userResponseDto);
    }
}
