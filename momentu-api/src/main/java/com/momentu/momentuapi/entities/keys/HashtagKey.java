package com.momentu.momentuapi.entities.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class HashtagKey implements Serializable {

    @Column(name="label")
    private String label;

    @Column(name="location_id")
    private Long locationId;

    public HashtagKey() {}

    public HashtagKey(String label, Long locationId) {
        this.label = label;
        this.locationId = locationId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
