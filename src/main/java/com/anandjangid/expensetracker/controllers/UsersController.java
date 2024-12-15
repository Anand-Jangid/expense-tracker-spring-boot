package com.anandjangid.expensetracker.controllers;

import com.anandjangid.expensetracker.dtos.users.UserResponseDto;
import com.anandjangid.expensetracker.dtos.users.UserUpdateDto;
import com.anandjangid.expensetracker.exceptions.UnauthorizedException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.jwt.JWTUtil;
import com.anandjangid.expensetracker.services.UsersService;
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
    public ResponseEntity<UserResponseDto> getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header is missing or malformed");
        }

        String token = authorizationHeader.substring(7);
        String userId = jwtUtil.extractUserId(token);
        var userProfile = userService.getUserById(UUID.fromString(userId));
        return ResponseEntity.ok(userProfile);
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserResponseDto> updateUserProfile(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserUpdateDto userUpdateDto) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header is missing or malformed");
        }
        String token = authorizationHeader.substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));

        UserResponseDto userResponseDto = userService.updateUser(userId, userUpdateDto);
        if(userResponseDto == null) throw new UserNotFoundException("user with id: " + userId + " not found");
        return ResponseEntity.ok(userResponseDto);
    }
}
