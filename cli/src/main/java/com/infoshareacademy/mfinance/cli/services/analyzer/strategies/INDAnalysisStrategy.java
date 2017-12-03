package com.infoshareacademy.mfinance.cli.services.analyzer.strategies;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.model.arguments.INDArgs;
import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.model.results.INDResult;
import com.infoshareacademy.mfinance.cli.utils.converters.IndicatorCriteriaConverterUtil;
import com.infoshareacademy.mfinance.cli.utils.converters.IndicatorResultConverterUtil;
import com.infoshareacademy.mfinance.core.analyzer.InvestmentIndicator;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;

public class INDAnalysisStrategy implements AnalysisStrategy {
    private INDResult indResult;

    @Override
    public AnalysisResult getResult(ParserResult result, DataContainer container) {
        INDArgs args = (INDArgs) result.getArguments();
        InvestmentIndicator investmentIndicator = new InvestmentIndicator(container,
                IndicatorCriteriaConverterUtil.convertFrom(args));
        try {
            indResult = IndicatorResultConverterUtil.convertFrom(investmentIndicator.getResult());
        } catch (NoDataForCriteria e) {
            System.out.println(e.getMessage());
        }
        return indResult;
    }
}
