package com.momentu.momentuapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="hashtag")
public class Hashtag {

    @EmbeddedId
    LabelLocationId labelLocationId;

    @Column(name="count")
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Embeddable
    public static class LabelLocationId implements Serializable {

        @Column(name="label")
        private String label;

        @Column(name="location_id")
        private Long locationId;

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
}
