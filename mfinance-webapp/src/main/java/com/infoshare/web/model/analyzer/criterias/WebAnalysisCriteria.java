package com.infoshare.web.model.analyzer.criterias;

public abstract class WebAnalysisCriteria {



    protected String strategy;

    private String userCustomName;

    private boolean isFavourite;

    public boolean isFavourite() {
        return isFavourite;
    }

    public String getStrategy() {
        return strategy;
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
}
