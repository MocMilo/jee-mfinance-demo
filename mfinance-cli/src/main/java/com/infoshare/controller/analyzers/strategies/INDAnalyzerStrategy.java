package com.infoshare.controller.analyzers.strategies;

import com.infoshare.controller.analyzers.converters.IndicatorCriteriaConverter;
import com.infoshare.controller.analyzers.converters.IndicatorResultConverter;
import com.infoshare.controller.analyzers.initializer.BossaInitializerFacade;
import com.infoshare.core.analyzer.analyses.indicator.Indicator;
import com.infoshare.core.models.analyses.results.IndicatorResult;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.view.composers.results.INDResultMessageComposer;


public class INDAnalyzerStrategy implements AnalysisStrategy {

    private INDResultMessageComposer composer = new INDResultMessageComposer();
    private BossaInitializerFacade initializer = new BossaInitializerFacade();
    private IndicatorCriteriaConverter criteriaConverter = new IndicatorCriteriaConverter();
    private IndicatorResultConverter resultConverter = new IndicatorResultConverter();

    @Override
    public void doAnalyzeAlgorithm(String[] args) throws NoDataForCriteria {
        IndicatorResult result =  getResult(args);
        composer.printResultMessage(resultConverter.convertFrom(result));
    }

    private IndicatorResult getResult(String[] args) throws NoDataForCriteria {

        Indicator indicator =
                new Indicator(initializer.getMainContainerWithLoadedData(),
                        criteriaConverter.convertFrom(args));

        return indicator.getResult();
    }
}
