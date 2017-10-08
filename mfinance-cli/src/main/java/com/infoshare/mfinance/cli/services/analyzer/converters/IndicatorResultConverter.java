package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.mfinance.core.models.analyses.results.IndicatorResult;
import com.infoshare.mfinance.cli.model.results.INDResult;
import org.modelmapper.ModelMapper;

public class IndicatorResultConverter {

    private ModelMapper mapper = new ModelMapper();

    public INDResult convertFrom(IndicatorResult result) {

        return mapper.map(result, INDResult.class);
    }
}

