package com.infoshare.model.criterias;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IVRCriteria {

    private static final int COMMAND_ARGS_NUMBER = 5;
    private static final String ANALYSIS_COMMAND_STRING = "IVR";
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
}
