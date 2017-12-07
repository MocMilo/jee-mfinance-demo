package com.infoshareacademy.mfinance.core.models.analyzer.results;

import java.math.BigDecimal;

public class InvestmentRevenueResult extends AnalysisResult {
    private BigDecimal capitalRevenueValue;
    private BigDecimal capitalRevenueDeltaPercentValue;

    public InvestmentRevenueResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPercentValue) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPercentValue = capitalRevenueDeltaPercentValue;
    }

    public BigDecimal getCapitalRevenueValue() {
        return capitalRevenueValue;
    }

    public BigDecimal getCapitalRevenueDeltaPercentValue() {
        return capitalRevenueDeltaPercentValue;
    }

}
