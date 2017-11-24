package com.infoshareacademy.web.services.analyzer;


import com.infoshareacademy.mfinance.core.analyzer.InvestmentRevenue;
import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshareacademy.web.utils.converters.InvestmentRevenueCriteriaConverter;
import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.analyzer.criterias.WebInvestmentRevenueCriteria;
import com.infoshareacademy.web.model.analyzer.results.WebAnalysisResult;
import com.infoshareacademy.web.model.analyzer.results.WebInvestmentRevenueResult;
import com.infoshareacademy.web.services.bossa.IDataContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IVRAnalysisStrategy implements AnalysisStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(IVRAnalysisStrategy.class);

    private WebInvestmentRevenueCriteria investmentRevenueCriteria;
    private WebInvestmentRevenueResult webResult;
    private InvestmentRevenueCriteriaConverter converter = new InvestmentRevenueCriteriaConverter();


    @Override
    public WebAnalysisResult getResult(WebAnalysisCriteria criteria, IDataContainerService container) {

        investmentRevenueCriteria = (WebInvestmentRevenueCriteria) criteria;

        InvestmentRevenueCriteria coreCriteria = converter.convertFrom(investmentRevenueCriteria);

        InvestmentRevenue revenue = new InvestmentRevenue(container.getDataContainer(), coreCriteria);

        try {
            InvestmentRevenueResult revenueResult = revenue.getResult();

            //todo impl Converter
            webResult = new WebInvestmentRevenueResult(revenueResult.getCapitalRevenueValue(),
                    revenueResult.getCapitalRevenueDeltaPercentValue());

        } catch (NoDataForCriteria e) {

            LOGGER.error("Failed to get WebAnalysisResult: {}",e.getMessage());

            webResult = new WebInvestmentRevenueResult();
            webResult.setErrorMessage("No data for current criteria, try again with different criteria.");
        }
        return webResult;
    }
}
