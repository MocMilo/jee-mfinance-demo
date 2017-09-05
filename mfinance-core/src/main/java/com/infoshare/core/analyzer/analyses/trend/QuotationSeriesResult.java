package com.infoshare.core.analyzer.analyses.trend;

import com.infoshare.core.analyzer.analyses.AnalysisResult;
import com.infoshare.core.model.Quotation;

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
