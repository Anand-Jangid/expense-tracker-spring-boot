package com.anandjangid.expensetracker.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.anandjangid.expensetracker.dtos.group.GroupCreateDto;
import com.anandjangid.expensetracker.entities.Groups;
import com.anandjangid.expensetracker.repositories.GroupsRepository;

@Service
public class GroupsService {
    private final GroupsRepository groupsRepository;

    GroupsService(GroupsRepository groupsRepository){
        this.groupsRepository = groupsRepository;
    }

    public Groups createGroup(GroupCreateDto group, UUID userId){
        Groups createdGroup = new Groups();
        createdGroup.setName(group.getName());
        createdGroup.setCreatedBy(userId);
        return groupsRepository.save(createdGroup);
    }
}
