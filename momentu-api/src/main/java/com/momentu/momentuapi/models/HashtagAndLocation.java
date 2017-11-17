package com.momentu.momentuapi.models;

public class HashtagAndLocation {
    private String hashtagLabel;
    private String city;
    private String state;

    public String getHashtagLabel() {
        return hashtagLabel;
    }

    public void setHashtagLabel(String hashtagLabel) {
        String label = hashtagLabel.toLowerCase();
        label = label.replaceAll("^#+", "");
        label = "#".concat(label);
        this.hashtagLabel = label;
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

    public boolean isValid() {
        boolean valid = true;
        if(getHashtagLabel() == null || getHashtagLabel().length() == 0) {
            valid = false;
        }
        if(getState() == null || getState().length() == 0) {
            valid = false;
        }
        if(getCity() == null || getCity().length() == 0) {
            valid = false;
        }
        return valid;
    }
}
