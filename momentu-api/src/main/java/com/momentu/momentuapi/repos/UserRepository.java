package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.User;
import com.momentu.momentuapi.entities.projections.PartialUserProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PartialUserProjection.class)
public interface UserRepository extends CrudRepository<User, Long> {

}
