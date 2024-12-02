package com.anandjangid.expensetracker.dtos.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String name;
    private String email;
    private String password;
}
