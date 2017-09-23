package com.infoshare.model.criterias;

import com.infoshare.model.arguments.IVRArgs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IVRCriteria {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
    private BigDecimal capital;
    private String investmentName;
    private LocalDate startDate;
    private LocalDate endDate;

    public BigDecimal getCapital() {
        return capital;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public IVRCriteria(BigDecimal capital, String investmentName,
                       LocalDate startDate, LocalDate endDate) {

        this.capital = capital;
        this.investmentName = investmentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public IVRCriteria(IVRArgs args) {

        this.capital = new BigDecimal(args.getCapital());
        this.investmentName =args.getInvestmentName();
        this.startDate = LocalDate.parse(args.getStartDate(), formatter);
        this.endDate = LocalDate.parse(args.getEndDate(), formatter);
    }
}
