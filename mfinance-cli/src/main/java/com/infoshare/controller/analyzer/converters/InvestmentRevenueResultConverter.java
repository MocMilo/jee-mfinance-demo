package com.infoshare.controller.analyzer.converters;

import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.model.analysisResults.IVRResult;



public class InvestmentRevenueResultConverter {

    public IVRResult convertFrom(InvestmentRevenueResult result) {

        return new IVRResult(result.getCapitalRevenueDeltaPrecentValue(),
                result.getCapitalRevenueValue());
    }
}
