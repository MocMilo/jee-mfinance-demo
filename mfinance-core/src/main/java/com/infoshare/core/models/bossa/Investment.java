package com.infoshare.core.models.bossa;

import java.util.ArrayList;
import java.util.List;

public abstract class Investment {

    private int Id;
    private String name;
    private List<Quotation> quotations = new ArrayList<>();

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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

    public Investment(int id, String name, List<Quotation> quotations) {
        Id = id;
        this.name = name;
        this.quotations = quotations;

    }

    @Override
    public String toString() {
        return "models{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", quotations=" + quotations +
                '}';
    }
}

