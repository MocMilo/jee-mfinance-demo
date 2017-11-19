package com.infoshare.web.services.analyzer.strategies;


import com.infoshare.mfinance.core.analyzer.revenue.InvestmentRevenue;
import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.mfinance.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.web.utils.converters.InvestmentRevenueCriteriaConverter;
import com.infoshare.web.model.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.criterias.WebInvestmentRevenueCriteria;
import com.infoshare.web.model.results.WebAnalysisResult;
import com.infoshare.web.model.results.WebInvestmentRevenueResult;
import com.infoshare.web.services.bossa.IDataContainerService;
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
        }
        return webResult;
    }
}
