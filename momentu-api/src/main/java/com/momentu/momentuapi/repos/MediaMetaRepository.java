package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.MediaMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface MediaMetaRepository extends CrudRepository<MediaMeta, Long> {

    @Query("select m from MediaMeta m left join fetch m.location lo " +
            "where lo.state=:state and lo.city=:city order by m.created desc")
    List<MediaMeta> findByStateCity(@Param("state") String state, @Param("city") String city);

    @Query("select m from MediaMeta m left join fetch m.location lo " +
            "where lo.state=:state order by m.created desc")
    List<MediaMeta> findByState(@Param("state") String state);

    @Query("select m from MediaMeta m left join m.location lo " +
            "where lo.state=:state and lo.city=:city and m.hashtagLabel=:label " +
            "order by m.created desc")
    Page<MediaMeta> findByStateCityLabel(@Param("state") String state, @Param("city") String city,
                                         @Param("label") String label, Pageable pageable);

    @Query("select m from MediaMeta m left join fetch m.location lo " +
            "where lo.state=:state and m.hashtagLabel=:label " +
            "order by m.created desc")
    List<MediaMeta> findByStateLabel(@Param("state") String state, @Param("label") String label);

    @Override
    @RestResource(exported=false)
    MediaMeta save(MediaMeta s);

    @Override
    @RestResource(exported=false)
    void delete(Long id);

    @Override
    @RestResource(exported=false)
    void delete(MediaMeta entity);
}
