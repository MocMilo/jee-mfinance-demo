package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.mfinance.cli.model.arguments.IVRArgs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvestmentRevenueCriteriaConverter {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public InvestmentRevenueCriteria convertFrom(IVRArgs ivrArgs) {

        return new InvestmentRevenueCriteria(new BigDecimal(ivrArgs.getCapital()),
                LocalDate.parse(ivrArgs.getStartDate(), formatter),
                LocalDate.parse(ivrArgs.getEndDate(), formatter),
                ivrArgs.getInvestmentName());
    }
}
