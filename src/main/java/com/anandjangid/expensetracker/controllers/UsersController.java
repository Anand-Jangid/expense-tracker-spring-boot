package com.anandjangid.expensetracker.controllers;

import com.anandjangid.expensetracker.dtos.users.UserResponseDto;
import com.anandjangid.expensetracker.dtos.users.UserUpdateDto;
import com.anandjangid.expensetracker.exceptions.UnauthorizedException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.jwt.JWTUtil;
import com.anandjangid.expensetracker.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/users")
@RestController
public class UsersController {
    private final UsersService userService;
    private final JWTUtil jwtUtil;

    public UsersController(UsersService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getUserProfile(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        var userProfile = userService.getUserById(UUID.fromString(userId));
        return ResponseEntity.ok(userProfile);
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserResponseDto> updateUserProfile(HttpServletRequest request, @RequestBody UserUpdateDto userUpdateDto) {
        UUID userId = UUID.fromString((String) request.getAttribute("userId"));

        UserResponseDto userResponseDto = userService.updateUser(userId, userUpdateDto);
        if(userResponseDto == null) throw new UserNotFoundException("user with id: " + userId + " not found");
        return ResponseEntity.ok(userResponseDto);
    }
}
