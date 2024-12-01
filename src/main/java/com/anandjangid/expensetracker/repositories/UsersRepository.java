package com.anandjangid.expensetracker.repositories;

import com.anandjangid.expensetracker.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
}
