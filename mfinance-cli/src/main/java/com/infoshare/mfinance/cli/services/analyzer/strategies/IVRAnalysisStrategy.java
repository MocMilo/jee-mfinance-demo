package com.infoshare.mfinance.cli.services.analyzer.strategies;

import com.infoshare.mfinance.core.analyzer.analyses.revenue.InvestmentRevenue;
import com.infoshare.mfinance.core.models.bossa.MainContainer;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.mfinance.cli.model.arguments.IVRArgs;
import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.model.results.IVRResult;
import com.infoshare.mfinance.cli.services.analyzer.converters.InvestmentRevenueCriteriaConverter;
import com.infoshare.mfinance.cli.services.analyzer.converters.InvestmentRevenueResultConverter;
import com.infoshare.mfinance.cli.services.parser.ParserResult;

public class IVRAnalysisStrategy implements AnalysisStrategy{

    private IVRResult ivrResult;
    private InvestmentRevenueCriteriaConverter criteriaConverter = new InvestmentRevenueCriteriaConverter();
    private InvestmentRevenueResultConverter resultConverter = new InvestmentRevenueResultConverter();


    @Override
    public AnalysisResult getResult(ParserResult result, MainContainer container) {

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
