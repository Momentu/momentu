package com.momentu.momentuapi.entities.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserLikeKey implements Serializable {

    @Column(name="user_id")
    private Long userId;

    @Column(name="media_meta_id")
    private Long mediaMetaId;

    public UserLikeKey() {}

    public UserLikeKey(Long userId, Long mediaMetaId) {
        this.userId = userId;
        this.mediaMetaId = mediaMetaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMediaMetaId() {
        return mediaMetaId;
    }

    public void setMediaMetaId(Long mediaMetaId) {
        this.mediaMetaId = mediaMetaId;
    }
}
