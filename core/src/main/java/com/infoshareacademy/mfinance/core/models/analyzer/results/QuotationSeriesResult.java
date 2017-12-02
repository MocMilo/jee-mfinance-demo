package com.infoshareacademy.mfinance.core.models.analyzer.results;

import com.infoshareacademy.mfinance.core.models.bossa.Quotation;

import java.util.ArrayList;
import java.util.List;

public class QuotationSeriesResult extends AnalysisResult {
    private List<Quotation> quotationList = new ArrayList<>();

    public QuotationSeriesResult(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public List<Quotation> getQuotationList() {
        return quotationList;
    }
}
