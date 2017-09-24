package com.infoshare.model.analysisResults;



import java.math.BigDecimal;

public class IVRResult {

    private BigDecimal capitalRevenueValue;
    private BigDecimal capitalRevenueDeltaPercentValue;

    public BigDecimal getCapitalRevenueValue() {
        return capitalRevenueValue;
    }
    public BigDecimal getCapitalRevenueDeltaPercentValue() {
        return capitalRevenueDeltaPercentValue;
    }

    public IVRResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPercentValue) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPercentValue = capitalRevenueDeltaPercentValue;
    }

}
