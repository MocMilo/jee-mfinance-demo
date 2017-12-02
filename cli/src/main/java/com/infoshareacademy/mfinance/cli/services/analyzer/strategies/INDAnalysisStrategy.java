package com.infoshareacademy.mfinance.cli.services.analyzer.strategies;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.model.arguments.INDArgs;
import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.model.results.INDResult;
import com.infoshareacademy.mfinance.cli.utils.converters.IndicatorCriteriaConverter;
import com.infoshareacademy.mfinance.cli.utils.converters.IndicatorResultConverter;
import com.infoshareacademy.mfinance.core.analyzer.InvestmentIndicator;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;

public class INDAnalysisStrategy implements AnalysisStrategy {
    private INDResult indResult;
    private IndicatorCriteriaConverter criteriaConverter = new IndicatorCriteriaConverter();
    private IndicatorResultConverter resultConverter = new IndicatorResultConverter();

    @Override
    public AnalysisResult getResult(ParserResult result, DataContainer container) {
        INDArgs args = (INDArgs) result.getArguments();
        InvestmentIndicator investmentIndicator = new InvestmentIndicator(container,
                criteriaConverter.convertFrom(args));
        try {
            indResult = resultConverter.convertFrom(investmentIndicator.getResult());
        } catch (NoDataForCriteria e) {
            System.out.println(e.getMessage());
        }
        return indResult;
    }
}
