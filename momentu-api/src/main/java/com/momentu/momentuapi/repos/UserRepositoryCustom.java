package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.User;

import java.security.Principal;
import java.util.Optional;

public interface UserRepositoryCustom {

    public Optional<User> getUserByPrincipal(Principal principal);
}
