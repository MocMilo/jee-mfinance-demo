package com.infoshareacademy.web.services.analyzer;

import com.infoshareacademy.mfinance.core.analyzer.InvestmentRevenue;
import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshareacademy.web.utils.converters.InvestmentRevenueCriteriaConverterUtil;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.model.analyzer.results.WebInvestmentRevenueResult;
import com.infoshareacademy.web.services.bossa.DataContainerService;
import com.infoshareacademy.web.utils.converters.WebInvestmentRevenueResultConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IVRAnalysisStrategy implements AnalysisStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(IVRAnalysisStrategy.class);

    @Override
    public WebAnalysisResult getResult(WebAnalysisCriteria criteria, DataContainerService container) {
        final WebInvestmentRevenueCriteria investmentRevenueCriteria = (WebInvestmentRevenueCriteria) criteria;
        final InvestmentRevenueCriteria coreCriteria = InvestmentRevenueCriteriaConverterUtil
                .convertFrom(investmentRevenueCriteria);

        final InvestmentRevenue revenue = new InvestmentRevenue(container.getDataContainer(), coreCriteria);

        WebInvestmentRevenueResult webResult;
        try {
            InvestmentRevenueResult revenueResult = revenue.getResult();
            webResult = WebInvestmentRevenueResultConverterUtil
                    .convertFrom(revenueResult);

        } catch (NoDataForCriteria e) {
            webResult = new WebInvestmentRevenueResult();
            webResult.setErrorMessage("No data for current criteria, try again with different criteria.");
            LOGGER.error("Failed to get WebAnalysisResult: {}", e.getMessage());
        }
        return webResult;
    }
}
