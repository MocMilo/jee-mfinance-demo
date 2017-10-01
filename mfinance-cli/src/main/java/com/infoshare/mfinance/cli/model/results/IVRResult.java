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

    public IVRResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPercentValue) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPercentValue = capitalRevenueDeltaPercentValue;
    }


    @Override
    public String toString() {
        return "\nInvestment Revenue [PLN]:" + capitalRevenueValue +
                ", Change [%]:" + capitalRevenueDeltaPercentValue + "\n";
    }

}
