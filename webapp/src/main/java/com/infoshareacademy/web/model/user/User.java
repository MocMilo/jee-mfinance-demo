package com.infoshareacademy.web.model.user;

import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String pass;
    private boolean isAdmin;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastLoginDateTime;
    private LocalDateTime lastUpdateDateTime;

    @PrePersist
    private void onCreate() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        lastUpdateDateTime = LocalDateTime.now();
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Set<WebInvestmentRevenueCriteria> favouritesIVR = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private Set<WebIndicatorCriteria> favouritesIND = new LinkedHashSet<>();

    public String getPass() {
        return pass;
    }

    public void setPass(String name) {
        this.pass = name;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public LocalDateTime getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(LocalDateTime lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public Set<WebInvestmentRevenueCriteria> getFavouritesIVR() {
        return favouritesIVR;
    }

    public void setFavouritesIVR(Set<WebInvestmentRevenueCriteria> favourites) {
        this.favouritesIVR = favourites;
    }

    public Set<WebIndicatorCriteria> getFavouritesIND() {
        return favouritesIND;
    }

    public void setFavouritesIND(Set<WebIndicatorCriteria> favouriteInvestmentIndicators) {
        this.favouritesIND = favouriteInvestmentIndicators;
    }

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

}
