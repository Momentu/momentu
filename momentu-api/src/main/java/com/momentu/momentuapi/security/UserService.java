package com.momentu.momentuapi.security;

import com.momentu.momentuapi.entities.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getByUsername(String username);
}
