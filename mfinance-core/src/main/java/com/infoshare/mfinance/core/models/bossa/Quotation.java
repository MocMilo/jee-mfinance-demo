package com.infoshare.mfinance.core.models.bossa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Quotation implements Comparable<Quotation> {

    private String name;
    private LocalDate date;
    private BigDecimal close;
    private BigDecimal deltaClose;

    public Quotation(String name, LocalDate date, BigDecimal close) {
        this.name = name;
        this.date = date;
        this.close = close;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getDeltaClose() {
        return deltaClose;
    }

    public void setDeltaClose(BigDecimal deltaClose) {
        this.deltaClose = deltaClose;
    }

    @Override
    public String toString() {
        return "Quotation [name=" + name + ", date=" + date +
                ", " + "close=" +close+
                ", deltaClose="+deltaClose+"%] \n";

    }

    @Override
    public int compareTo(Quotation o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }
}
