package com.infoshare.web.model.criterias;

import com.infoshare.web.services.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.web.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WebIndicatorCriteria extends WebAnalysisCriteria {

    private static final String STRATEGY = "IND";

    @ManyToOne
    private User user;

    @ManyToOne
    private AnalysisComparisonContainer analysisComparisonContainer;

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
    public WebIndicatorCriteria(String investmentName, String userCustomName, boolean isFavouriteChecked) {
            this.setInvestmentName(investmentName);
            this.setFavourite(isFavouriteChecked);
            this.setUserCustomName(userCustomName);
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

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
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

    @PrePersist
    private void onCreate() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        lastUpdateDateTime = LocalDateTime.now();
    }


    public AnalysisComparisonContainer getAnalysisComparisonContainer() {
        return analysisComparisonContainer;
    }

    public void setAnalysisComparisonContainer(AnalysisComparisonContainer analysisComparisonContainer) {
        this.analysisComparisonContainer = analysisComparisonContainer;
    }


}