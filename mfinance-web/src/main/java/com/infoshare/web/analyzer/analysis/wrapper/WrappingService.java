package com.infoshare.web.analyzer.analysis.wrapper;


import com.infoshare.web.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.infoshare.web.analyzer.analysis.investmentindicator.PersistedIndicatorCriteria;
import com.infoshare.core.analyzer.analyses.exception.NoDataForCriteria;
import com.infoshare.core.analyzer.analyses.indicator.Indicator;
import com.infoshare.core.analyzer.analyses.indicator.IndicatorCriteria;
import com.infoshare.core.analyzer.analyses.indicator.IndicatorResult;
import com.infoshare.web.container.IModelContainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class WrappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WrappingService.class);

    @Inject
    IModelContainerService container;

    public List<ComparisonContentWrapper> getWrapperedContentList(List<AnalysisComparisonContainer> containers)
            throws NoDataForCriteria  {

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

    private ComparisonContentWrapper getWrapperedContent1(List<PersistedIndicatorCriteria> criteriaList)
            throws NoDataForCriteria {

        ComparisonContentWrapper wrapper = new ComparisonContentWrapper();

        for (PersistedIndicatorCriteria criteria : criteriaList) {
            IndicatorResult result = new Indicator(container.getMainContainer(),
                    new IndicatorCriteria(criteria.getInvestmentName())).getResult();

            wrapper.setUserCustomName(criteria.getUserCustomName());
            wrapper.getAnanysisContentList().add(new AnalysisContent(criteria,result));
        }
        return wrapper;
    }

}
