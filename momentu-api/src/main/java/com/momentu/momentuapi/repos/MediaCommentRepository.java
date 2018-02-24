package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.MediaComment;
import com.momentu.momentuapi.entities.keys.MediaCommentKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MediaCommentRepository extends CrudRepository<MediaComment, MediaCommentKey> {
    @Query("select m from MediaComment m " +
            "where m.key.mediaMeta.id=:mediaMetaId " +
            "order by m.key.createdDate desc")
    Page<MediaComment> findByMediaMetaId(@Param("mediaMetaId") Long mediaMetaId, Pageable pageable);
}
