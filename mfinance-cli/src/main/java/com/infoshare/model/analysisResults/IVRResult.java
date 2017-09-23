package com.infoshare.model.analysisResults;



import java.math.BigDecimal;

public class IVRResult {

    private BigDecimal capitalRevenueValue;
    private BigDecimal capitalRevenueDeltaPrecentValue;

    public BigDecimal getCapitalRevenueValue() {
        return capitalRevenueValue;
    }
    public BigDecimal getCapitalRevenueDeltaPrecentValue() {
        return capitalRevenueDeltaPrecentValue;
    }

    public IVRResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPrecentValue) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPrecentValue = capitalRevenueDeltaPrecentValue;
    }

}
