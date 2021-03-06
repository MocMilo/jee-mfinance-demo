package com.infoshareacademy.mfinance.cli.model.results;

import java.math.BigDecimal;

public class IVRResult extends AnalysisResult {
    private BigDecimal capitalRevenueValue;
    private BigDecimal capitalRevenueDeltaPercentValue;

    public BigDecimal getCapitalRevenueValue() {
        return capitalRevenueValue;
    }

    public BigDecimal getCapitalRevenueDeltaPrecentValue() {
        return capitalRevenueDeltaPercentValue;
    }

    public void setCapitalRevenueValue(BigDecimal capitalRevenueValue) {
        this.capitalRevenueValue = capitalRevenueValue;
    }

    public void setCapitalRevenueDeltaPercentValue(BigDecimal capitalRevenueDeltaPercentValue) {
        this.capitalRevenueDeltaPercentValue = capitalRevenueDeltaPercentValue;
    }

    @Override
    public String toString() {
        return "IVRResult{" +
                "capitalRevenueValue=" + capitalRevenueValue +
                ", capitalRevenueDeltaPercentValue=" + capitalRevenueDeltaPercentValue +
                '}';
    }
}
