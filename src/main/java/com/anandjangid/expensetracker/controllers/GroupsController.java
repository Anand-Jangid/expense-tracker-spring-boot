package com.anandjangid.expensetracker.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anandjangid.expensetracker.dtos.group.GroupAddUserDto;
import com.anandjangid.expensetracker.dtos.group.GroupCreateDto;
import com.anandjangid.expensetracker.entities.Groups;
import com.anandjangid.expensetracker.entities.Users;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<Users>> getGroupDetail(@PathVariable UUID id){
        List<Users> users = groupsService.getGroupDetail(id);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping
    public Groups createGroup(@Valid @RequestBody GroupCreateDto groupCreateDto, HttpServletRequest httpServletRequest){
        UUID userId = UUID.fromString((String) httpServletRequest.getAttribute("userId"));
        return groupsService.createGroup(groupCreateDto, userId);
    }

    @PutMapping("/add-user")
    public ResponseEntity addUser(@Valid @RequestBody GroupAddUserDto groupAddUserDto){
        groupsService.addUserToGroup(groupAddUserDto.getGroupId(), groupAddUserDto.getUserId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
