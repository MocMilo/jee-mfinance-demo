package com.infoshare.controller.analyzers.converters;

import com.infoshare.core.models.analyses.results.IndicatorResult;
import com.infoshare.model.analysisResults.INDResult;


public class IndicatorResultConverter {

    QuotationToCLIQuotationConverter converter = new QuotationToCLIQuotationConverter();

    public INDResult convertFrom(IndicatorResult result) {

        return new INDResult(
                result.getName(),
                converter.convertFrom(result.getFirstQuotation()),
                converter.convertFrom(result.getLastQuotation()),
                converter.convertFrom(result.getMaxValueQuotation()),
                converter.convertFrom(result.getMinValueQuotation()),
                converter.convertFrom(result.getMaxDeltaPlus()),
                converter.convertFrom(result.getMaxDeltaMinus()));

    }
}

