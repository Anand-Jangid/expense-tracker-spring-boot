package com.anandjangid.expensetracker.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_group")
public class UserGroup {
    @EmbeddedId
    private UserGroupId id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "group_id")
    private Groups group;

    private LocalDateTime joinedAt;
}
