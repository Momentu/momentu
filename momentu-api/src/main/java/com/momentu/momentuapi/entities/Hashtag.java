package com.momentu.momentuapi.entities;

import com.momentu.momentuapi.entities.keys.HashtagKey;

import javax.persistence.*;

@Entity
@Table(name="hashtag")
public class Hashtag {

    @EmbeddedId
    private HashtagKey hashtagKey;

    @Column(name="count")
    private Long count;

    @Column(name="latitude", nullable=true)
    private Long latitude;

    @Column(name="longitude", nullable=true)
    private Long longitude;

    public Hashtag() {}

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public HashtagKey getHashtagKey() {
        return hashtagKey;
    }

    public void setHashtagKey(HashtagKey hashtagKey) {
        this.hashtagKey = hashtagKey;
    }

    public String getLabel() {
        return hashtagKey.getLabel();
    }

    public Location getLocation() {
        return hashtagKey.getLocation();
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
