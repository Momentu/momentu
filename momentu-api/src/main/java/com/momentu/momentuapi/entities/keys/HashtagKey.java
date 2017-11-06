package com.momentu.momentuapi.entities.keys;

import com.momentu.momentuapi.entities.Location;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class HashtagKey implements Serializable {

    @Column(name="label")
    private String label;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="location_id")
    private Location location;

    public HashtagKey() {}

    public HashtagKey(String label, Long locationId) {
        this.label = label;
        this.location.setId(locationId);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getLocationId() {
        return this.location.getId();
    }

    public void setLocationId(Long locationId) {
        this.location.setId(locationId);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
