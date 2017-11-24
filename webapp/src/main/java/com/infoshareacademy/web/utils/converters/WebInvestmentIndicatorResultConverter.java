package com.infoshareacademy.web.utils.converters;


import com.infoshareacademy.mfinance.core.models.analyzer.results.IndicatorResult;

import com.infoshareacademy.web.model.analyzer.results.WebIndicatorResult;
import org.modelmapper.ModelMapper;

public class WebInvestmentIndicatorResultConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public WebIndicatorResult convertFrom(IndicatorResult coreResult) {

        return modelMapper.map(coreResult, WebIndicatorResult.class);
    }
}
