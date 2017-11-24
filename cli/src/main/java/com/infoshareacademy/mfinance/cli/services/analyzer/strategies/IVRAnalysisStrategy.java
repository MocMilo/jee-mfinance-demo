package com.infoshareacademy.mfinance.cli.services.analyzer.strategies;

import com.infoshareacademy.mfinance.cli.utils.converters.InvestmentRevenueCriteriaConverter;
import com.infoshareacademy.mfinance.core.analyzer.InvestmentRevenue;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshareacademy.mfinance.cli.model.arguments.IVRArgs;
import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.model.results.IVRResult;
import com.infoshareacademy.mfinance.cli.utils.converters.InvestmentRevenueResultConverter;
import com.infoshareacademy.mfinance.cli.model.ParserResult;

public class IVRAnalysisStrategy implements AnalysisStrategy{

    private IVRResult ivrResult;
    private InvestmentRevenueCriteriaConverter criteriaConverter = new InvestmentRevenueCriteriaConverter();
    private InvestmentRevenueResultConverter resultConverter = new InvestmentRevenueResultConverter();


    @Override
    public AnalysisResult getResult(ParserResult result, DataContainer container) {

        IVRArgs args = (IVRArgs) result.getArguments();

        InvestmentRevenue revenue =
                new InvestmentRevenue(container, criteriaConverter.convertFrom(args));

        try {
            ivrResult = resultConverter.convertFrom(revenue.getResult());
        } catch (NoDataForCriteria e) {
            System.out.println(e.getMessage());
        }
        return ivrResult;
    }
}
