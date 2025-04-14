package com.anandjangid.expensetracker.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anandjangid.expensetracker.entities.UserGroup;
import com.anandjangid.expensetracker.entities.UserGroupId;
import com.anandjangid.expensetracker.entities.Users;

public interface UserGroupRepository extends JpaRepository<UserGroup, UserGroupId>{
    @Query("SELECT ug.user FROM UserGroup ug WHERE ug.group.id = :groupId")
    List<Users> findUserByGroupId(@Param("groupId") UUID groupId);
}