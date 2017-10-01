package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.cli.model.results.IVRResult;


public class InvestmentRevenueResultConverter {

    public IVRResult convertFrom(InvestmentRevenueResult result) {

        return new IVRResult(result.getCapitalRevenueDeltaPrecentValue(),
                result.getCapitalRevenueValue());
    }
}
