package com.momentu.momentuapi.entities.keys;

import com.momentu.momentuapi.entities.Role;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
public class UserRoleKey implements Serializable {

    @Column(name="user_id")
    protected Long userId;

    public UserRoleKey() { }

    public UserRoleKey(Long userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    protected Role role;
}