package com.momentu.momentuapi.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="media_meta")
public class MediaMeta extends AbstractEntity {
    @Column(name="user_id")
    private Long userId;

    @Column(name="hashtag_label")
    private String hashtagLabel;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="location_id")
    private Location location;

    @Column(name="created")
    private Date created;

    @Column(name="removed")
    private boolean removed;

    public MediaMeta() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHashtagLabel() {
        return hashtagLabel;
    }

    public void setHashtagLabel(String hashtagLabel) {
        this.hashtagLabel = hashtagLabel;
    }

    public Long getLocationId() {
        return this.location.getId();
    }

    public void setLocationId(Long locationId) {
        this.location.setId(locationId);
    }

    public Location getLocation() { return this.location; }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
