package com.momentu.momentuapi.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class PasswordResetRequest {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    private User user;

    private Date expiryDate;

    private String hashedToken;

    public PasswordResetRequest() {}

    public PasswordResetRequest(User user, String hashedToken) {
        this.user = user;
        this.expiryDate = this.calculateExpiryDate(EXPIRATION);
        this.hashedToken = hashedToken;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }

    public String getHashedToken() {
        return hashedToken;
    }

    public void setHashedToken(String hashedToken) {
        this.hashedToken = hashedToken;
    }
}
