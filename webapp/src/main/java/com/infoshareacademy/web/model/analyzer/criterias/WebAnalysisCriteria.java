package com.infoshareacademy.web.model.analyzer.criterias;

public abstract class WebAnalysisCriteria {
    protected String strategy;
    protected String userCustomName;
    protected boolean isFavourite;

    public String getStrategy() {
        return strategy;
    }

    public String getUserCustomName() {
        return userCustomName;
    }

    public boolean isFavourite() {
        return isFavourite;
    }
}
