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
}
