
package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.core.models.analyses.criteria.IndicatorCriteria;
import com.infoshare.mfinance.cli.model.arguments.INDArgs;

public class IndicatorCriteriaConverter {

    public IndicatorCriteria convertFrom(INDArgs indArgs) {

        return new IndicatorCriteria(indArgs.getInvestmentName());
    }
}
