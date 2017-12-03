package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.results.IndicatorResult;
import com.infoshareacademy.mfinance.cli.model.results.INDResult;
import org.modelmapper.ModelMapper;

public class IndicatorResultConverterUtil {
    private static ModelMapper mapper = new ModelMapper();

    public static INDResult convertFrom(IndicatorResult result) {
        return mapper.map(result, INDResult.class);
    }
}

