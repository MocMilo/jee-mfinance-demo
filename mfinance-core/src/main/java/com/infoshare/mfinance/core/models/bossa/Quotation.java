package com.infoshare.mfinance.core.models.bossa;

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
        if (name == null) {
            throw new IllegalArgumentException("Quotation name should not be null.");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Quotation name should not be empty.");
        }

        if (date == null) {
            throw new IllegalArgumentException("Quotation date should not be null.");
        }

        if (close == null) {
            throw new IllegalArgumentException("Quotation close value should not be null.");
        }

        this.name = name;
        this.date = date;
        this.close = close;
    }

    @Override
    public String toString() {
        return "Quotation [name=" + name + ", date=" + date +
                ", " + "close=" + close +
                ", deltaClose=" + deltaClose + "%] \n";
    }

    @Override
    public int compareTo(Quotation o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }
}
