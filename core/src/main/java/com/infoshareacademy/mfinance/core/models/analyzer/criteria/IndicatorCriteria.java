package com.infoshareacademy.mfinance.core.models.analyzer.criteria;


public class IndicatorCriteria extends AnalysisCriteria {

    private String investmentName;

    public String getInvestmentName() {
        return this.investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }


    public IndicatorCriteria() {
    }

    public IndicatorCriteria(String investmentName) {
        this.investmentName = investmentName;
    }
}
