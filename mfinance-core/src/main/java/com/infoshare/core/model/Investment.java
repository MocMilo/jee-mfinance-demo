package com.infoshare.core.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Investment {

    public int Id;
    public String name;
    public List<Quotation> quotations = new ArrayList<>();

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
        return "model{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", quotations=" + quotations +
                '}';
    }
}

