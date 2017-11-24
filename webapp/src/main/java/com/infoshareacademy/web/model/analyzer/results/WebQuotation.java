package com.infoshareacademy.web.model.analyzer.results;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WebQuotation  {

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }


}
