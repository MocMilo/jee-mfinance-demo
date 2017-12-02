package com.infoshareacademy.web.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import org.modelmapper.ModelMapper;

public class InvestmentRevenueCriteriaConverterUtil {
    private static ModelMapper modelMapper = new ModelMapper();

    public static InvestmentRevenueCriteria convertFrom(WebInvestmentRevenueCriteria webCriteria) {
        return modelMapper.map(webCriteria, InvestmentRevenueCriteria.class);
    }
}
