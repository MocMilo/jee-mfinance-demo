package com.infoshare.controller.analyzers.converters;

import com.infoshare.core.models.bossa.Quotation;
import com.infoshare.model.analysisResults.CLIQuotation;


public class QuotationToCLIQuotationConverter {

    public CLIQuotation convertFrom(Quotation quotation) {

        CLIQuotation cliQuotation = new CLIQuotation(
                quotation.getName(),
                quotation.getDate(),
                quotation.getOpen(),
                quotation.getHigh(),
                quotation.getLow(),
                quotation.getClose(),
                quotation.getVolume());
        return cliQuotation;
    }
}
