package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.PasswordResetRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface PasswordResetRequestRepository extends CrudRepository<PasswordResetRequest, Long> {

    @Query("select p from PasswordResetRequest p where p.hashedToken=:hashedToken")
    public Optional<PasswordResetRequest> findByHashedToken(@Param("hashedToken") String hashedToken);
}
