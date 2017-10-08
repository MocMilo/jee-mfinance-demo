package com.infoshare.mfinance.core.models.analyses.results;

import com.infoshare.mfinance.core.models.bossa.Quotation;

import java.util.ArrayList;
import java.util.List;

public class QuotationSeriesResult extends AnalysisResult {

    List<Quotation> quotationList = new ArrayList<>();

    public QuotationSeriesResult(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public List<Quotation> getQuotationList() {
        return quotationList;
    }
}
