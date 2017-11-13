package com.momentu.momentuandroid.Model;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Jane on 11/13/2017.
 */

public class Like {

    private int likesCount;
    private boolean isLiked;


    public Like(int likesCount, boolean isLiked) {
        this.likesCount = likesCount;
        this.isLiked = isLiked;
    }

    public int getLikesCount(){
        return likesCount;
    }

    public boolean getIsLiked(){
        return isLiked;
    }

    public void setIsLiked(boolean isLiked){
        this.isLiked = isLiked;
    }

    public void addLikesCount(){
        likesCount++;
    }

    public void minusLikesCount(){
        likesCount--;
    }

}
