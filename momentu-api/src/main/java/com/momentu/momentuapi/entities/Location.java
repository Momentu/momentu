package com.momentu.momentuapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location extends AbstractEntity {

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    public Location() {}

    public Location(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
