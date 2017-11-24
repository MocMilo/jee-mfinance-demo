package com.infoshareacademy.web.model.analyzer.criterias;

import com.infoshareacademy.web.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WebIndicatorCriteria extends WebAnalysisCriteria {

    private static final String STRATEGY = "IND";

    @ManyToOne
    private User user;

    @Id
    @GeneratedValue
    private long id;
    private String investmentName;

    private LocalDateTime creationDateTime;
    private LocalDateTime lastUpdateDateTime;
    private String userCustomName;

    private boolean isFavourite;

    public WebIndicatorCriteria() {
        this.strategy = STRATEGY;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    @PrePersist
    private void onCreate() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        lastUpdateDateTime = LocalDateTime.now();
    }


}



