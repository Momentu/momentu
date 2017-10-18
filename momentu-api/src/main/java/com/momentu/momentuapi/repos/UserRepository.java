package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u left join fetch u.user_role r where u.username=:username")
    public Optional<User> findByUsername(@Param("username") String username);
}
