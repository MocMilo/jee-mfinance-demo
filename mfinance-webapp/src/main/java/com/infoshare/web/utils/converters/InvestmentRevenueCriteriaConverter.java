package com.infoshare.web.utils.converters;

import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.web.model.criterias.WebInvestmentRevenueCriteria;
import org.modelmapper.ModelMapper;


public class InvestmentRevenueCriteriaConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public InvestmentRevenueCriteria convertFrom(WebInvestmentRevenueCriteria webCriteria) {

        return modelMapper.map(webCriteria, InvestmentRevenueCriteria.class);
    }

}
