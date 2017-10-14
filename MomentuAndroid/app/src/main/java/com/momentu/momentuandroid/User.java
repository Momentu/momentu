package com.momentu.momentuandroid;

/**
 * Created by Jane on 10/11/2017.
 */

public class User {
    //TODO: make them private later
    public long userId;
    public String username;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String gender;


    public User(long userId, String username, String password){
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

}