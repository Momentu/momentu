package com.momentu.momentuapi.entities.keys;

import com.momentu.momentuapi.entities.MediaComment;
import com.momentu.momentuapi.entities.MediaMeta;
import com.momentu.momentuapi.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class MediaCommentKey implements Serializable {

    @ManyToOne()
    private User user;

    @ManyToOne
    private MediaMeta mediaMeta;

    @Column(name="created_date")
    private Date createdDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MediaMeta getMediaMeta() {
        return mediaMeta;
    }

    public void setMediaMeta(MediaMeta mediaMeta) {
        this.mediaMeta = mediaMeta;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
