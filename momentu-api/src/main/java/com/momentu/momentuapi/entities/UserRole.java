package com.momentu.momentuapi.entities;

import com.momentu.momentuapi.entities.keys.UserRoleKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_role")
public class UserRole {

    @EmbeddedId
    UserRoleKey userRoleKey = new UserRoleKey();

    @Enumerated(EnumType.STRING)
    @Column(name="role", insertable=false, updatable=false)
    protected Role role;

    public Role getRole() {
        return role;
    }

    public UserRole() {}

    public UserRole(Long userId, Role role) {
        userRoleKey = new UserRoleKey(userId, role);
    }
}
