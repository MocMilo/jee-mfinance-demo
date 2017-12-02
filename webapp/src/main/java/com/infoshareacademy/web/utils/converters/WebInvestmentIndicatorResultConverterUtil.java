package com.infoshareacademy.web.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.results.IndicatorResult;
import com.infoshareacademy.web.model.analyzer.results.WebIndicatorResult;
import org.modelmapper.ModelMapper;

public class WebInvestmentIndicatorResultConverterUtil {
    private static ModelMapper modelMapper = new ModelMapper();

    public static WebIndicatorResult convertFrom(IndicatorResult coreResult) {
        return modelMapper.map(coreResult, WebIndicatorResult.class);
    }
}
