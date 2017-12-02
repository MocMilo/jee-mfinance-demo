package com.infoshareacademy.web.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import com.infoshareacademy.web.model.analyzer.results.WebInvestmentRevenueResult;
import org.modelmapper.ModelMapper;

public class WebInvestmentRevenueResultConverterUtil {
    private static ModelMapper modelMapper = new ModelMapper();

    public static WebInvestmentRevenueResult convertFrom(InvestmentRevenueResult coreResult) {
        return modelMapper.map(coreResult, WebInvestmentRevenueResult.class);
    }
}
