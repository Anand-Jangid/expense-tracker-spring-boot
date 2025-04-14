package com.anandjangid.expensetracker.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anandjangid.expensetracker.dtos.group.GroupCreateDto;
import com.anandjangid.expensetracker.entities.Groups;
import com.anandjangid.expensetracker.entities.UserGroup;
import com.anandjangid.expensetracker.entities.Users;
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
        Optional<Users> user = usersRepository.findById(userId);
        Groups createdGroup = new Groups();
        createdGroup.setName(group.getName());
        createdGroup.setCreatedBy(userId);
        var savedGroup = groupsRepository.save(createdGroup);

        UserGroup ug = new UserGroup();
        ug.setGroup(createdGroup);
        ug.setUser(user.get());
        ug.setJoinedAt(LocalDateTime.now());
        userGroupRepository.save(ug);
        return savedGroup;
    }

    public void addUserToGroup(UUID groupId, UUID userId){
        var group = groupsRepository.findById(groupId);
        var user = usersRepository.findById(userId);

        var userGroup = new UserGroup();
        userGroup.setGroup(group.orElseThrow(() -> new GroupNotFoundException("Group not found with given id.")));
        userGroup.setUser(user.orElseThrow(() -> new UserNotFoundException("User not found with given id.")));
        userGroupRepository.save(userGroup);
    }

    public List<Users> getGroupDetail(UUID groupId){
        return userGroupRepository.findUserByGroupId(groupId);
    }
}
