package com.momentu.momentuapi.entities;

import com.momentu.momentuapi.entities.keys.UserLikeKey;

import javax.persistence.*;

@Entity
@Table(name="user_like")
public class UserLike {

    @EmbeddedId
    private UserLikeKey userLikeKey;

    public UserLikeKey getUserLikeKey() {
        return userLikeKey;
    }

    public void setUserLikeKey(UserLikeKey userLikeKey) {
        this.userLikeKey = userLikeKey;
    }

}
