package com.momentu.momentuapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="media_meta")
public class MediaMeta extends AbstractEntity {
    @Column(name="user_id")
    private Long userId;

    @Column(name="hashtag_label")
    private String hashtagLabel;

    @Column(name="location_id")
    private Long locationId;

    @Column(name="created")
    private Date created;

    @Column(name="removed")
    private boolean removed;

    public MediaMeta() {}

    public MediaMeta(Long userId, String hashtagLabel, Long locationId) {
        this.userId = userId;
        this.hashtagLabel = hashtagLabel;
        this.locationId = locationId;
        setCreated(new Date());
        setRemoved(false);
    }

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
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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
