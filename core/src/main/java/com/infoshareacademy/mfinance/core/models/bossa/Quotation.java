package com.infoshareacademy.mfinance.core.models.bossa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Quotation implements Comparable<Quotation> {
    private String name;
    private LocalDate date;
    private BigDecimal close;
    private BigDecimal deltaClose;

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getDeltaClose() {
        return deltaClose;
    }

    public void setDeltaClose(BigDecimal deltaClose) {
        this.deltaClose = deltaClose;
    }

    public Quotation(String name, LocalDate date, BigDecimal close) {
        this.name = name;
        this.date = date;
        this.close = close;
    }

    @Override
    public int compareTo(Quotation o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }
}
