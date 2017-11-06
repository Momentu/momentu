package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.MediaMeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface MediaMetaRepository extends CrudRepository<MediaMeta, Long> {

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
