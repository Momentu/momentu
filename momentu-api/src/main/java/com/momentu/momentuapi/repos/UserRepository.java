package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(@Param("username") String username);
}
