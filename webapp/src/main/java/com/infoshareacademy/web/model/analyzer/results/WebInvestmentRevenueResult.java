package com.infoshareacademy.web.model.analyzer.results;

import java.math.BigDecimal;

public class WebInvestmentRevenueResult extends WebAnalysisResult {
    private static final String STRATEGY = "IVR";
    private BigDecimal capitalRevenueValue;
    private BigDecimal capitalRevenueDeltaPercentValue;

    public BigDecimal getCapitalRevenueValue() {
        return capitalRevenueValue;
    }

    public BigDecimal getCapitalRevenueDeltaPercentValue() {
        return capitalRevenueDeltaPercentValue;
    }

    public void setCapitalRevenueValue(BigDecimal capitalRevenueValue) {
        this.capitalRevenueValue = capitalRevenueValue;
    }

    public void setCapitalRevenueDeltaPercentValue(BigDecimal capitalRevenueDeltaPercentValue) {
        this.capitalRevenueDeltaPercentValue = capitalRevenueDeltaPercentValue;
    }

    public WebInvestmentRevenueResult() {
        this.strategy = STRATEGY;
    }

    public WebInvestmentRevenueResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPercentValue) {
        this.strategy = STRATEGY;
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPercentValue = capitalRevenueDeltaPercentValue;
    }
}
