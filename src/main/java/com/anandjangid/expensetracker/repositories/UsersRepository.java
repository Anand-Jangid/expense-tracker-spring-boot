package com.anandjangid.expensetracker.repositories;

import com.anandjangid.expensetracker.entities.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email);
}
