package com.infoshareacademy.mfinance.core.models.analyzer.results;

import java.math.BigDecimal;

public class InvestmentRevenueResult extends AnalysisResult {
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

    public InvestmentRevenueResult() {
    }

    public InvestmentRevenueResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPercentValue) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPercentValue = capitalRevenueDeltaPercentValue;
    }
}
