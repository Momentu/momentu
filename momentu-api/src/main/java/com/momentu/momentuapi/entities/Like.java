package com.momentu.momentuapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="like")
public class Like {

    @EmbeddedId
    private LikePK likePK;

    @Embeddable
    public static class LikePK implements Serializable {

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "meta_media_id")
        private Long metaMediaId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getMetaMediaId() {
            return metaMediaId;
        }

        public void setMetaMediaId(Long metaMediaId) {
            this.metaMediaId = metaMediaId;
        }
    }
}
