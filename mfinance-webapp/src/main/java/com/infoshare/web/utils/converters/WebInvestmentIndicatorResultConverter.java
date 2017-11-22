package com.infoshare.web.utils.converters;


import com.infoshare.mfinance.core.models.analyses.results.IndicatorResult;

import com.infoshare.web.model.analyzer.results.WebIndicatorResult;
import org.modelmapper.ModelMapper;

public class WebInvestmentIndicatorResultConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public WebIndicatorResult convertFrom(IndicatorResult coreResult) {

        return modelMapper.map(coreResult, WebIndicatorResult.class);
    }
}
