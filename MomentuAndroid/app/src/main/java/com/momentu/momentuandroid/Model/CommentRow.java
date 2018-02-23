package com.momentu.momentuandroid.Model;

/**
 * Created by Fahad on 2/20/18.
 */

public class CommentRow {

    private String username;
    private String comment;


    public CommentRow(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }


    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }
}
