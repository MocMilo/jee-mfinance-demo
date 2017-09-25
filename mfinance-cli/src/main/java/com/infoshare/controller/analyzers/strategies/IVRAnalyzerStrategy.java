package com.infoshare.controller.analyzers.strategies;

import com.infoshare.controller.analyzers.initializer.BossaInitializerFacade;
import com.infoshare.controller.analyzers.converters.InvestmentRevenueCriteriaConverter;
import com.infoshare.controller.analyzers.converters.InvestmentRevenueResultConverter;
import com.infoshare.core.analyzer.analyses.revenue.InvestmentRevenue;
import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.view.composers.IVRResultMessageComposer;

public class IVRAnalyzerStrategy implements AnalysisStrategy {

    private IVRResultMessageComposer composer = new IVRResultMessageComposer();
    private InvestmentRevenueResultConverter resultConverter = new InvestmentRevenueResultConverter();
    private BossaInitializerFacade initializer = new BossaInitializerFacade();
    private InvestmentRevenueCriteriaConverter criteriaConverter = new InvestmentRevenueCriteriaConverter();

    @Override
    public void doAnalyzeAlgorithm(String[] args) throws NoDataForCriteria {
        InvestmentRevenueResult result =  getResult(args);
        composer.printResultMessage(resultConverter.convertFrom(result));
    }

    public InvestmentRevenueResult getResult(String[] args) throws NoDataForCriteria {

        InvestmentRevenue revenue =
                new InvestmentRevenue(initializer.getMainContainerWithLoadedData(),
                        criteriaConverter.convertFrom(args));

        return revenue.getResult();
    }
}