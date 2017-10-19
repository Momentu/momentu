package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RepositoryRestResource
@RequestMapping("/api/users")
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u left join fetch u.roles r where u.username=:username")
    public Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from User u left join fetch u.roles r where u.email=:email")
    public Optional<User> findByEmail(@Param("email") String email);
}
