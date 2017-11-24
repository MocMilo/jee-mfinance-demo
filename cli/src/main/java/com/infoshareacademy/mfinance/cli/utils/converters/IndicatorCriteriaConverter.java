
package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.IndicatorCriteria;
import com.infoshareacademy.mfinance.cli.model.arguments.INDArgs;

public class IndicatorCriteriaConverter {

    public IndicatorCriteria convertFrom(INDArgs indArgs) {

        return new IndicatorCriteria(indArgs.getInvestmentName());
    }
}
