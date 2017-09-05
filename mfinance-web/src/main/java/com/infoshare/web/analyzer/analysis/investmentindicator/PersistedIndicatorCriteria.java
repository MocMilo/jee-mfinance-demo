package com.infoshare.web.analyzer.analysis.investmentindicator;

import com.infoshare.web.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.core.analyzer.analyses.indicator.IndicatorCriteria;
import com.infoshare.web.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PersistedIndicatorCriteria extends IndicatorCriteria {

    @ManyToOne
    private User user;

    @ManyToOne
    private AnalysisComparisonContainer analysisComparisonContainer;


    private LocalDateTime creationDateTime;
    private LocalDateTime lastUpdateDateTime;
    private String userCustomName;

    public PersistedIndicatorCriteria() {
    }
    public PersistedIndicatorCriteria(String investmentName, String userCustomName, boolean isFavouriteChecked) {
            this.setInvestmentName(investmentName);
            this.setFavourite(isFavouriteChecked);
            this.setUserCustomName(userCustomName);
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
