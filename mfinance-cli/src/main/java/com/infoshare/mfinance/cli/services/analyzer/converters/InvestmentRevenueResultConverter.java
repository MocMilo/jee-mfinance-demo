package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.cli.model.results.IVRResult;
import org.modelmapper.ModelMapper;


public class InvestmentRevenueResultConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public IVRResult convertFrom(InvestmentRevenueResult result) {

        return modelMapper.map(result, IVRResult.class);
    }
}
