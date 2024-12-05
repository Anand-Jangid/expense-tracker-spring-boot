package com.anandjangid.expensetracker.dtos.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    @Email
    @NotBlank(message = "name is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
}
