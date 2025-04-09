package com.anandjangid.expensetracker.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserGroupId implements Serializable {
    private UUID userId;
    private UUID groupId;

    public UserGroupId(UUID userId, UUID groupId){
        this.userId = userId;
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof UserGroupId)) return false;
        UserGroupId that = (UserGroupId) obj;
        return Objects.equals(userId, that.userId) && Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
    
}
