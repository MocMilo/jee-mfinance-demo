
package com.infoshare.controller.analyzers.converters;
import com.infoshare.core.models.analyses.criteria.IndicatorCriteria;
import com.infoshare.model.arguments.INDArgs;


public class IndicatorCriteriaConverter {

    public IndicatorCriteria convertFrom(String[] args) {

        INDArgs indArgs = new INDArgs(args);
        return new IndicatorCriteria(indArgs.getInvestmentName());
    }
}
