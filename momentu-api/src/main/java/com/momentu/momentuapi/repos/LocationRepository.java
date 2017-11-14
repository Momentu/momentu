package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query("select l from Location l where l.state=:state")
    List<Location> findByState(@Param("state") String state);

    @Query("select l from Location l where l.city=:city and l.state=:state")
    Optional<Location> findByStateCity(@Param("city") String city, @Param("state") String state);

    @Override
    @RestResource(exported=false)
    Location save(Location s);

    @Override
    @RestResource(exported = false)
    void delete(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Location entity);
}
