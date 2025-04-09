package com.anandjangid.expensetracker.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anandjangid.expensetracker.dtos.group.GroupCreateDto;
import com.anandjangid.expensetracker.entities.Groups;
import com.anandjangid.expensetracker.entities.UserGroup;
import com.anandjangid.expensetracker.exceptions.groups.GroupNotFoundException;
import com.anandjangid.expensetracker.exceptions.users.UserNotFoundException;
import com.anandjangid.expensetracker.repositories.GroupsRepository;
import com.anandjangid.expensetracker.repositories.UserGroupRepository;
import com.anandjangid.expensetracker.repositories.UsersRepository;

@Service
public class GroupsService {
    private final GroupsRepository groupsRepository;
    private final UsersRepository usersRepository;
    private final UserGroupRepository userGroupRepository;

    GroupsService(GroupsRepository groupsRepository, UsersRepository usersRepository, UserGroupRepository userGroupRepository){
        this.groupsRepository = groupsRepository;
        this.usersRepository = usersRepository;
        this.userGroupRepository = userGroupRepository;
    }

    public Groups createGroup(GroupCreateDto group, UUID userId){
        Groups createdGroup = new Groups();
        createdGroup.setName(group.getName());
        createdGroup.setCreatedBy(userId);
        return groupsRepository.save(createdGroup);
    }

    public void addUserToGroup(UUID groupId, UUID userId){
        var group = groupsRepository.findById(groupId);
        var user = usersRepository.findById(userId);

        var userGroup = new UserGroup();
        userGroup.setGroup(group.orElseThrow(() -> new GroupNotFoundException("Group not found with given id.")));
        userGroup.setUser(user.orElseThrow(() -> new UserNotFoundException("User not found with given id.")));
        userGroupRepository.save(userGroup);
    }
}
