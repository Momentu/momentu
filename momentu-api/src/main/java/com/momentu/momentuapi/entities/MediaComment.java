package com.momentu.momentuapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.momentu.momentuapi.entities.keys.MediaCommentKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="media_comments")
@AssociationOverrides({
        @AssociationOverride(name="key.user",
            joinColumns = @JoinColumn(name="user_id")),
        @AssociationOverride(name="key.mediaMeta",
            joinColumns = @JoinColumn(name="media_meta_id"))
})
public class MediaComment implements Serializable  {

    @EmbeddedId
    private MediaCommentKey key = new MediaCommentKey();

    @Column(name="comment")
    private String comment;

    public MediaCommentKey getKey() {
        return key;
    }

    public void setKey(MediaCommentKey key) {
        this.key = key;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonIgnore
    public User getUser() {
        return key.getUser();
    }

    public void setUser(User user) {
        key.setUser(user);
    }

    public String getUsername() {
        return key.getUser().getUsername();
    }

    @JsonIgnore
    public MediaMeta getMediaMeta() {
        return key.getMediaMeta();
    }

    public void setMediaMeta(MediaMeta mediaMeta) {
        key.setMediaMeta(mediaMeta);
    }

    public Date getCreatedDate() {
        return key.getCreatedDate();
    }

    public void setCreatedDate(Date createdDate) {
        key.setCreatedDate(createdDate);
    }
}
