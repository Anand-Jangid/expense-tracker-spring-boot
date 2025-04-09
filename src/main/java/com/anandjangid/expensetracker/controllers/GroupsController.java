package com.anandjangid.expensetracker.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anandjangid.expensetracker.dtos.group.GroupCreateDto;
import com.anandjangid.expensetracker.entities.Groups;
import com.anandjangid.expensetracker.services.GroupsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    
    private final GroupsService groupsService;

    GroupsController(GroupsService groupsService){
        this.groupsService = groupsService;
    }

    @PostMapping
    public Groups createGroup(@Valid @RequestBody GroupCreateDto groupCreateDto, HttpServletRequest httpServletRequest){
        UUID userId = UUID.fromString((String) httpServletRequest.getAttribute("userId"));
        return groupsService.createGroup(groupCreateDto, userId);
    }
}
