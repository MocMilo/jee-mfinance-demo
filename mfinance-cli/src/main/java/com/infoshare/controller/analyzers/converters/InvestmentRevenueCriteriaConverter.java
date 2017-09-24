package com.infoshare.controller.analyzers.converters;

import com.infoshare.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.model.arguments.IVRArgs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvestmentRevenueCriteriaConverter {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public InvestmentRevenueCriteria convertFrom(String[] args) {

        IVRArgs ivrArgs = new IVRArgs(args);
        return new InvestmentRevenueCriteria(new BigDecimal(ivrArgs.getCapital()),
                LocalDate.parse(ivrArgs.getStartDate(), formatter),
                LocalDate.parse(ivrArgs.getEndDate(), formatter),
                ivrArgs.getInvestmentName());
    }
}
