package com.infoshare.mfinance.cli.services.analyzer.strategies;


import com.infoshare.core.analyzer.analyses.indicator.Indicator;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.mfinance.cli.model.arguments.INDArgs;
import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.model.results.INDResult;
import com.infoshare.mfinance.cli.services.analyzer.converters.IndicatorCriteriaConverter;
import com.infoshare.mfinance.cli.services.analyzer.converters.IndicatorResultConverter;
import com.infoshare.mfinance.cli.services.parser.ParserResult;

public class INDAnalysisStrategy implements AnalysisStrategy {

    private INDResult indResult;
    private IndicatorCriteriaConverter criteriaConverter = new IndicatorCriteriaConverter();
    private IndicatorResultConverter resultConverter = new IndicatorResultConverter();


    @Override
    public AnalysisResult getResult(ParserResult result, MainContainer container) {

        INDArgs args = (INDArgs) result.getArguments();
        
        Indicator indicator= new Indicator(container, criteriaConverter.convertFrom(args));

        try {
            indResult = resultConverter.convertFrom(indicator.getResult());
        } catch (NoDataForCriteria e) {
            System.out.println(e.getMessage());
        }
        return indResult;
    }

}
