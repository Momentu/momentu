package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.Hashtag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported=true)
public interface HashtagRepository extends CrudRepository<Hashtag, Hashtag.LabelLocationId> {

//    @Query("select h from Hashtag h where h.label=:label and h.locationId=:locationId")
//    Optional<Hashtag> findByLabelAndLocationId(@Param("label") String label,
//                                                 @Param("locationId") Long locationId);
}
