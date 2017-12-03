package com.infoshareacademy.mfinance.cli.model.results.embeded;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CLIQuotation {
    private String name;
    private LocalDate date;
    private BigDecimal close;
    private BigDecimal deltaClose;

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
}
