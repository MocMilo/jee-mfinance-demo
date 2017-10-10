package com.infoshare.mfinance.core.models.bossa;

import java.util.ArrayList;
import java.util.List;

public abstract class Investment {


    private String name;
    private List<Quotation> quotations = new ArrayList<>();



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "models{" +
                ", name='" + name + '\'' +
                ", quotations=" + quotations +
                '}';
    }
}

