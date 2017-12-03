package com.infoshareacademy.mfinance.core.models.bossa;

import java.util.ArrayList;
import java.util.List;

public abstract class Investment {
    private String name;
    private List<Quotation> quotations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public Investment(String name, List<Quotation> quotations) {
        this.name = name;
        this.quotations = quotations;
    }
}

