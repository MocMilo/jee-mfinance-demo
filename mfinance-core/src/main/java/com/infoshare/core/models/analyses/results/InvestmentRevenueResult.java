package com.infoshare.core.models.analyses.results;

import java.math.BigDecimal;

public class InvestmentRevenueResult extends AnalysisResult {

    private BigDecimal capitalRevenueValue;
    private BigDecimal capitalRevenueDeltaPrecentValue;

    public BigDecimal getCapitalRevenueValue() {
        return capitalRevenueValue;
    }

    public BigDecimal getCapitalRevenueDeltaPrecentValue() {
        return capitalRevenueDeltaPrecentValue;
    }

    public InvestmentRevenueResult() {
    }

    public InvestmentRevenueResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPrecentValue) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPrecentValue = capitalRevenueDeltaPrecentValue;
    }
}
