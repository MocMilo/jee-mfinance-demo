package com.infoshare.core.models.analyses.results;

import com.infoshare.core.models.analyses.criteria.AnalysisCriteria;

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

    public InvestmentRevenueResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPrecentValue, AnalysisCriteria evaluatedInput) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPrecentValue = capitalRevenueDeltaPrecentValue;
        super.finallyEvaluatedInput = evaluatedInput;
    }
}
