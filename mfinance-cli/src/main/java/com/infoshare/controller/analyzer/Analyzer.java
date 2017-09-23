package com.infoshare.controller.analyzer;

import com.infoshare.controller.analyzer.converters.InvestmentRevenueCriteriaConverter;
import com.infoshare.controller.analyzer.converters.InvestmentRevenueResultConverter;
import com.infoshare.core.analyzer.analyses.revenue.InvestmentRevenue;
import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.view.IVRResultMessageComposer;


public class Analyzer {

    private IVRResultMessageComposer composer = new IVRResultMessageComposer();
    private InvestmentRevenueResultConverter converter = new InvestmentRevenueResultConverter();

    public void doAnalyze(String[] args) throws NoDataForCriteria {
        InvestmentRevenueResult result =  getResult(args);

        composer.printResultMessage(converter.convertFrom(result));
    }

    public InvestmentRevenueResult getResult(String[] args) throws NoDataForCriteria {

        BossaInitializerFasade initializer = new BossaInitializerFasade();
        InvestmentRevenueCriteriaConverter converter = new InvestmentRevenueCriteriaConverter();

        InvestmentRevenue revenue =
                new InvestmentRevenue(initializer.getMainContainerWithLoadedData(),
                        converter.convertFrom(args));

        return revenue.getResult();
    }
}
