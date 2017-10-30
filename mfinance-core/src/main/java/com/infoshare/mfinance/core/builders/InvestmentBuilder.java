package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.builders.quotation.QuotationBuilder;
import com.infoshare.mfinance.core.models.bossa.Quotation;

import java.io.InputStream;
import java.util.List;

abstract class InvestmentBuilder {

    protected List<Quotation> getQuotationsList(String filePath) {
        QuotationBuilder builder = new QuotationBuilder();
        return builder.getQuotations(filePath);
    }

    protected List<Quotation> getQuotationsListFromStream(InputStream stream) {
        QuotationBuilder builder = new QuotationBuilder();
        return builder.getQuotations(stream);
    }
}
