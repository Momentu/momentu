package com.momentu.momentuapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="location")
public class Location extends AbstractEntity {

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy="hashtagKey.location")
    private List<Hashtag> hashtags;

    @OneToMany(mappedBy="location")
    private List<MediaMeta> mediaMetas;

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
