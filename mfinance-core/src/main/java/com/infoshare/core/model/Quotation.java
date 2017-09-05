package com.infoshare.core.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Quotation implements Comparable<Quotation> {

    private String name;
    private LocalDate date;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal deltaClose;

    public Quotation(String name, LocalDate date, BigDecimal open, BigDecimal high , BigDecimal low, BigDecimal close, BigDecimal volume) {
        this.name = name;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        // this.deltaClose =deltaClose;
        this.volume = volume;
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

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
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
              //  ", open=" + open +
              //  ", high=" + high +
              //  ", low=" +low+
                ", " + "close=" +close+
               // " , volume=" +volume+
                ", deltaClose="+deltaClose+"%] \n";

    }


    @Override
    public int compareTo(Quotation o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }

}
