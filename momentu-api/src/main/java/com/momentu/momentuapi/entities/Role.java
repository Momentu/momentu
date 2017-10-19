package com.momentu.momentuapi.entities;

public enum Role {
    ADMIN, EVENT_MEMBER, MEMBER;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
