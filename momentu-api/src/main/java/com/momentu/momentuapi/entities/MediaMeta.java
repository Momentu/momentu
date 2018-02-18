package com.momentu.momentuapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="media_meta")
public class MediaMeta extends AbstractEntity {

    @Column(name="hashtag_label")
    private String hashtagLabel;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="location_id")
    private Location location;

    @Column(name="created")
    private Date created;

    @Column(name="removed")
    private boolean removed;

    @Column(name="imageLocation")
    private String imageLocation;

    @Column(name="videoLocation")
    private String videoLocation;

    @Column(name="thumbnailLocation")
    private String thumbnailLocation;

    @Column(name="mediaType")
    private String mediaType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_likes",
        joinColumns = @JoinColumn(
                name = "media_meta_id",
                referencedColumnName = "id",
                nullable = false),
        inverseJoinColumns = @JoinColumn(
                name = "user_id",
                referencedColumnName = "id",
                nullable = false
        ))
    public Set<User> userLikes;

    public MediaMeta() {}

    public Long getId() { return this.id; }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Set<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Set<User> userLikes) {
        this.userLikes = userLikes;
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

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(String videoLocation) {
        this.videoLocation = videoLocation;
    }

    public String getThumbnailLocation() {
        return thumbnailLocation;
    }

    public void setThumbnailLocation(String thumbnailLocation) {
        this.thumbnailLocation = thumbnailLocation;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Long getLikeCount() {
        return getUserLikes().stream().count();
    }
}
