package com.momentu.momentuapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserRole {

    @EmbeddedId
    Id id = new Id();

    @Enumerated(EnumType.STRING)
    @Column(insertable=false, updatable=false)
    protected Role role;

    public Role getRole() {
        return role;
    }

    public UserRole() {}

    public UserRole(Long userId, Role role) {
        id = new Id(userId, role);
    }

    @Embeddable
    public static class Id implements Serializable {

        protected Long userId;

        public Id() { }

        public Id(Long userId, Role role) {
            this.userId = userId;
            this.role = role;
        }

        @Enumerated(EnumType.STRING)
        protected Role role;
    }
}
