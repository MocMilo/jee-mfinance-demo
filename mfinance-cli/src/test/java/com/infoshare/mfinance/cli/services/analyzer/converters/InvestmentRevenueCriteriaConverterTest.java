package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.mfinance.cli.model.arguments.IVRArgs;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvestmentRevenueCriteriaConverterTest {

    private IVRArgs ivrArgs;
    private InvestmentRevenueCriteriaConverter converter = new InvestmentRevenueCriteriaConverter();

    @Before
    public void init() {
        String[] args = {"IVR", "USD", "1000", "2015-09-08", "2015-09-07"};
        ivrArgs = new IVRArgs(args);
    }

    @Test
    public void convertFrom() throws Exception {

       //TODO impl. this
        
       InvestmentRevenueCriteria criteria = converter.convertFrom(ivrArgs);
       assertThat(criteria.getInvestmentName(), is(equalTo(ivrArgs.getInvestmentName())));


    }

}