package com.infoshareacademy.web.services.analyzer;

import com.infoshareacademy.mfinance.core.analyzer.InvestmentIndicator;
import com.infoshareacademy.mfinance.core.models.analyzer.criteria.IndicatorCriteria;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebIndicatorCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.model.analyzer.results.WebIndicatorResult;
import com.infoshareacademy.web.services.bossa.DataContainerService;
import com.infoshareacademy.web.utils.converters.InvestmentIndicatorCriteriaConverterUtil;
import com.infoshareacademy.web.utils.converters.WebInvestmentIndicatorResultConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class INDAnalysisStrategy implements AnalysisStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(INDAnalysisStrategy.class);

    @Override
    public WebAnalysisResult getResult(WebAnalysisCriteria criteria, DataContainerService container) {
        final WebIndicatorCriteria webIndicatorCriteria = (WebIndicatorCriteria) criteria;
        final IndicatorCriteria coreCriteria = InvestmentIndicatorCriteriaConverterUtil
                .convertFrom(webIndicatorCriteria);

        WebIndicatorResult webResult;
        try {
            InvestmentIndicator investmentIndicator = new InvestmentIndicator(container
                    .getDataContainer(), coreCriteria);
            webResult = WebInvestmentIndicatorResultConverterUtil
                    .convertFrom(investmentIndicator.getResult());
        } catch (NoDataForCriteria e) {
            webResult = new WebIndicatorResult();
            webResult.setErrorMessage("No data for current criteria, try again with different criteria.");
            LOGGER.error("Failed to get WebAnalysisResult: InvestmentIndicator {}", e.getMessage());
        }
        return webResult;
    }
}
