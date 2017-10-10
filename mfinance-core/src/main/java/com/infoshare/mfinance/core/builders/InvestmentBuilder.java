package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.builders.quotation.QuotationBuilder;
import com.infoshare.mfinance.core.models.bossa.Quotation;

import java.util.List;

public abstract class InvestmentBuilder {

    protected List<Quotation> getQuotationsList(String filePath) {
        QuotationBuilder builder = new QuotationBuilder();
        return builder.getQuotations(filePath);
    }
}
