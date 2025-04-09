package com.anandjangid.expensetracker.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anandjangid.expensetracker.entities.Groups;

public interface GroupsRepository extends JpaRepository<Groups, UUID>{}
