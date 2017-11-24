package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.results.IndicatorResult;
import com.infoshareacademy.mfinance.cli.model.results.INDResult;
import org.modelmapper.ModelMapper;

public class IndicatorResultConverter {

    private ModelMapper mapper = new ModelMapper();

    public INDResult convertFrom(IndicatorResult result) {

        return mapper.map(result, INDResult.class);
    }
}

