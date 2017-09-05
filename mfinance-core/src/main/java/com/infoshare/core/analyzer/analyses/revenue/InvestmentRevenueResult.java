package com.infoshare.core.analyzer.analyses.revenue;

import com.infoshare.core.analyzer.analyses.AnalysisCriteria;
import com.infoshare.core.analyzer.analyses.AnalysisResult;

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
