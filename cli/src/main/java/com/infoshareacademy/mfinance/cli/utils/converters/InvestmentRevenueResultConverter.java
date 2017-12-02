package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.cli.model.results.IVRResult;
import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import org.modelmapper.ModelMapper;


public class InvestmentRevenueResultConverter {
    private ModelMapper modelMapper = new ModelMapper();
    public IVRResult convertFrom(InvestmentRevenueResult result) {
        return modelMapper.map(result, IVRResult.class);
    }
}
