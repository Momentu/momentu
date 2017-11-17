package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.Hashtag;
import com.momentu.momentuapi.entities.keys.HashtagKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface HashtagRepository extends CrudRepository<Hashtag, HashtagKey> {

    @Query("select h from Hashtag h left join fetch h.hashtagKey.location lo where lo.state=:state and lo.city=:city")
    List<Hashtag> findByStateCity(@Param("state") String state, @Param("city") String city);

    @Query("select h from Hashtag h left join fetch h.hashtagKey.location lo " +
            "where lo.state=:state and lo.city=:city order by h.count desc")
    List<Hashtag> findByStateCityTrending(@Param("state") String state, @Param("city") String city);

    @Query("select h from Hashtag h left join fetch h.hashtagKey.location lo " +
            "where lo.state=:state and lo.city=:city and h.hashtagKey.label like concat('%',:label,'%')" +
            "order by h.count desc")
    List<Hashtag> findByStateCityTrendingLabel(@Param("state") String state, @Param("city") String city,
                                               @Param("label") String label);

    @Query("select h from Hashtag h where h.hashtagKey.label=:label and h.hashtagKey.location.id=:locationId")
    Optional<Hashtag> findByLabelAndLocationId(@Param("label") String label, @Param("locationId") Long locationId);

    @Override
    @RestResource(exported=false)
    Hashtag save(Hashtag s);

    @Override
    @RestResource(exported=false)
    void delete(Hashtag entity);
}
