package com.anandjangid.expensetracker.entities;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_group")
public class UserGroup {

    @EmbeddedId
    private UserGroupId id = new UserGroupId(); // safer to initialize it

    @ManyToOne
    @MapsId("userId") // map to field in UserGroupId
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @MapsId("groupId") // map to field in UserGroupId
    @JoinColumn(name = "group_id")
    private Groups group;

    private LocalDateTime joinedAt;

    @PrePersist
    public void prePersist() {
        this.joinedAt = LocalDateTime.now();
    }
}
