package com.infoshareacademy.web.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.IndicatorCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import org.modelmapper.ModelMapper;

public class InvestmentIndicatorCriteriaConverter {


    private ModelMapper modelMapper = new ModelMapper();

    public IndicatorCriteria convertFrom(WebIndicatorCriteria webCriteria) {

        return modelMapper.map(webCriteria, IndicatorCriteria.class);
    }

}
