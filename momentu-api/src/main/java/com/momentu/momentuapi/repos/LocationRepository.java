package com.momentu.momentuapi.repos;

import com.momentu.momentuapi.entities.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported=false)
public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query("select l from Location l where l.city=:city and l.state=:state")
    Optional<Location> findByCityAndState(@Param("city") String city, @Param("state") String state);
}
