package com.infoshare.web.services.analyzer.analysis.wrapper;



import com.infoshare.web.services.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.web.model.criterias.WebIndicatorCriteria;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import com.infoshare.mfinance.core.analyzer.indicator.InvestmentIndicator;
import com.infoshare.mfinance.core.models.analyses.criteria.IndicatorCriteria;
import com.infoshare.mfinance.core.models.analyses.results.IndicatorResult;
import com.infoshare.web.services.bossa.IDataContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class WrappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WrappingService.class);

    @Inject
    IDataContainerService container;

    public List<ComparisonContentWrapper> getWrapperedContentList(List<AnalysisComparisonContainer> containers)
            throws NoDataForCriteria {

        List<ComparisonContentWrapper> wrappers = new ArrayList<>();
        try {
            for (AnalysisComparisonContainer analysisContainer : containers) {
                wrappers.add(getWrapperedContent1(analysisContainer.getCriteriaList()));
            }

        } catch (Exception ex) {
            LOGGER.error("Wrapping content failure: {}", ex.getStackTrace());
        }
        return wrappers;
    }

    private ComparisonContentWrapper getWrapperedContent1(List<WebIndicatorCriteria> criteriaList)
            throws NoDataForCriteria {

        ComparisonContentWrapper wrapper = new ComparisonContentWrapper();

        for (WebIndicatorCriteria criteria : criteriaList) {
            IndicatorResult result = new InvestmentIndicator(container.getDataContainer(),
                    new IndicatorCriteria(criteria.getInvestmentName())).getResult();

            wrapper.setUserCustomName(criteria.getUserCustomName());
            //fixme
            wrapper.getAnanysisContentList().add(new AnalysisContent(criteria,result));
        }
        return wrapper;
    }

}
