package com.infoshareacademy.web.services.analyzer;


import com.infoshareacademy.mfinance.core.analyzer.InvestmentIndicator;
import com.infoshareacademy.mfinance.core.models.analyzer.criteria.IndicatorCriteria;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.model.analyzer.results.WebIndicatorResult;
import com.infoshareacademy.web.services.bossa.IDataContainerService;
import com.infoshareacademy.web.utils.converters.InvestmentIndicatorCriteriaConverter;
import com.infoshareacademy.web.utils.converters.WebInvestmentIndicatorResultConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class INDAnalysisStrategy implements AnalysisStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(INDAnalysisStrategy.class);

    private WebIndicatorCriteria webIndicatorCriteria;
    private InvestmentIndicatorCriteriaConverter webINDCriteriaConverter = new InvestmentIndicatorCriteriaConverter();
    private WebInvestmentIndicatorResultConverter webINDResultConverter = new WebInvestmentIndicatorResultConverter();
    private WebIndicatorResult webResult;

    @Override
    public WebAnalysisResult getResult(WebAnalysisCriteria criteria, IDataContainerService container) {

        webIndicatorCriteria = (WebIndicatorCriteria) criteria;

        IndicatorCriteria coreCriteria = webINDCriteriaConverter.convertFrom(webIndicatorCriteria);

        LOGGER.info(coreCriteria.getInvestmentName());

        try {
            webResult = webINDResultConverter.convertFrom(
                    new InvestmentIndicator(container.getDataContainer(), coreCriteria)
                            .getResult());

        } catch (NoDataForCriteria e) {

            LOGGER.error("Failed to get WebAnalysisResult: InvestmentIndicator {}", e.getMessage());

            webResult = new WebIndicatorResult();
            webResult.setErrorMessage("No data for current criteria, try again with different criteria.");
        }
        return webResult;
    }
}
