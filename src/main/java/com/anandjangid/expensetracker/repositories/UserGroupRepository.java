package com.anandjangid.expensetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anandjangid.expensetracker.entities.UserGroup;
import com.anandjangid.expensetracker.entities.UserGroupId;

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId>{}
