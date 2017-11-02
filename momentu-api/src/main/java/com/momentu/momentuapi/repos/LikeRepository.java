package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface LikeRepository extends CrudRepository<Like, Long> {

}
