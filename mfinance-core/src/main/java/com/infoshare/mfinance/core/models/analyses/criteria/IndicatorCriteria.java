package com.infoshare.mfinance.core.models.analyses.criteria;


public class IndicatorCriteria extends AnalysisCriteria {
    private String name;

    public String getName() {
        return name;
    }

    public IndicatorCriteria(String name) {
        this.name = name;
    }

    public IndicatorCriteria() {
    }
}
