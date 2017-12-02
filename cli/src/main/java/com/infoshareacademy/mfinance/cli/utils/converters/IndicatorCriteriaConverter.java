package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.IndicatorCriteria;
import com.infoshareacademy.mfinance.cli.model.arguments.INDArgs;
import org.modelmapper.ModelMapper;

public class IndicatorCriteriaConverter {

    private ModelMapper mapper = new ModelMapper();

    public IndicatorCriteria convertFrom(INDArgs indArgs) {
        return mapper.map(indArgs, IndicatorCriteria.class);
    }
}
