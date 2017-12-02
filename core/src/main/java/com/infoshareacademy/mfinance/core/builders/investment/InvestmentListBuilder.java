package com.infoshareacademy.mfinance.core.builders.investment;

import com.infoshareacademy.mfinance.core.builders.quotation.QuotationListBuilder;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;

import java.util.List;

abstract class InvestmentListBuilder {
    protected List<Quotation> getQuotationsList(String filePath) {
        return new QuotationListBuilder(filePath)
                .getQuotations();
    }
}
