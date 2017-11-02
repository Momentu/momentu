package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
