package com.anandjangid.expensetracker.controllers;

import com.anandjangid.expensetracker.dtos.users.UserLoginDto;
import com.anandjangid.expensetracker.dtos.users.UserRequestDto;
import com.anandjangid.expensetracker.dtos.users.UserTokenDto;
import com.anandjangid.expensetracker.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsersService usersService;
    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody UserLoginDto userLoginDto) {
        String token = usersService.loginUser(userLoginDto);
        UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setToken(token);
        return ResponseEntity.ok(userTokenDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserTokenDto> register(@RequestBody UserRequestDto userRequestDto) {
        String token = usersService.registerUser(userRequestDto);
        UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setToken(token);
        return ResponseEntity.ok(userTokenDto);
    }
}
