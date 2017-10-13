package com.momentu.momentuapi.entities.projections;

import com.momentu.momentuapi.entities.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "partial", types = {User.class})
public interface PartialUserProjection {

    String getUsername();

    String getFirstName();

    String getLastName();

    String getEmail();
}
