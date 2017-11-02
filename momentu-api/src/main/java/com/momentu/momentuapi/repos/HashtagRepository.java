package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.Hashtag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported=false)
public interface HashtagRepository extends CrudRepository<Hashtag, Long> {

    @Query("select h from Hashtag h where h.label=:hashtag and h.location_id=:location_id")
    Optional<Hashtag> findByHashtagAndLocationId(@Param("hashtag") String hashtag,
                                                 @Param("location_id") Long locationId);
}
