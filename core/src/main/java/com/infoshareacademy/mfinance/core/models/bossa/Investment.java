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

        if (name == null) {
            throw new IllegalArgumentException("Investment name should not be null");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Investment name should not be empty");
        }

        if (quotations == null) {
            throw new IllegalArgumentException("Investment quotations list should not be null");
        }

        if (quotations.isEmpty()) {
            throw new IllegalArgumentException("Investment quotations list should not be empty");
        }

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

