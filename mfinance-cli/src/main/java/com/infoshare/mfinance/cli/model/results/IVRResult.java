package com.infoshare.mfinance.cli.model.results;

import java.math.BigDecimal;

public class IVRResult extends AnalysisResult {


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

    public IVRResult() {
    }

    @Override
    public String toString() {
        return "IVRResult{" +
                "capitalRevenueValue=" + capitalRevenueValue +
                ", capitalRevenueDeltaPercentValue=" + capitalRevenueDeltaPercentValue +
                '}';
    }
}
