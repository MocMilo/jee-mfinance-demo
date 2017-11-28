package com.infoshareacademy.web.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleUserProfile {

    private String id;
    private String email;
    private String name;

    @JsonProperty("verified_email")
    private String vierifiedEmail;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("picture")
    private String pictureURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVierifiedEmail() {
        return vierifiedEmail;
    }

    public void setVierifiedEmail(String vierifiedEmail) {
        this.vierifiedEmail = vierifiedEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
