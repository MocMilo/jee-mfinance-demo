package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.cli.model.results.IVRResult;
import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import org.modelmapper.ModelMapper;

public class InvestmentRevenueResultConverterUtil {
    private static ModelMapper modelMapper = new ModelMapper();
    public static IVRResult convertFrom(InvestmentRevenueResult result) {
        return modelMapper.map(result, IVRResult.class);
    }
}
