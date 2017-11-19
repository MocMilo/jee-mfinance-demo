package com.infoshare.web.services.analyzer.strategies;


import com.infoshare.mfinance.core.analyzer.indicator.InvestmentIndicator;
import com.infoshare.mfinance.core.models.analyses.criteria.IndicatorCriteria;
import com.infoshare.mfinance.core.models.analyses.results.IndicatorResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.web.model.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.criterias.WebIndicatorCriteria;
import com.infoshare.web.model.results.WebAnalysisResult;
import com.infoshare.web.services.bossa.IDataContainerService;
import com.infoshare.web.utils.converters.InvestmentIndicatorCriteriaConverter;
import com.infoshare.web.utils.converters.WebInvestmentIndicatorResultConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class INDAnalysisStrategy implements AnalysisStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(INDAnalysisStrategy.class);

    private WebIndicatorCriteria webIndicatorCriteria;
    private InvestmentIndicatorCriteriaConverter converter = new InvestmentIndicatorCriteriaConverter();
    private WebInvestmentIndicatorResultConverter webConverter = new WebInvestmentIndicatorResultConverter();
    private IndicatorResult result;

    @Override
    public WebAnalysisResult getResult(WebAnalysisCriteria criteria, IDataContainerService container) {

        webIndicatorCriteria = (WebIndicatorCriteria) criteria;

        IndicatorCriteria coreCriteria = converter.convertFrom(webIndicatorCriteria);

        try {
            result = new InvestmentIndicator(container.getDataContainer(), coreCriteria).getResult();
        } catch (NoDataForCriteria e) {

            LOGGER.error("Failed to get WebAnalysisResult: InvestmentIndicator {}", e.getMessage());
        }

        return webConverter.convertFrom(result);
    }
}
