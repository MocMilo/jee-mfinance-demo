package com.infoshare.web.utils.converters;

import com.infoshare.mfinance.core.models.analyses.criteria.IndicatorCriteria;
import com.infoshare.web.model.analyzer.criterias.WebIndicatorCriteria;
import org.modelmapper.ModelMapper;

public class InvestmentIndicatorCriteriaConverter {


    private ModelMapper modelMapper = new ModelMapper();

    public IndicatorCriteria convertFrom(WebIndicatorCriteria webCriteria) {

        return modelMapper.map(webCriteria, IndicatorCriteria.class);
    }

}
