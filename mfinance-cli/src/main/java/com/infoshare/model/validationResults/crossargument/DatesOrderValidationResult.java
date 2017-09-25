package com.infoshare.model.validationResults.crossargument;

import java.time.LocalDate;

public class DatesOrderValidationResult {

    private LocalDate expectedAdFirst;
    private LocalDate expectedAsSecond;
    private boolean idValidOrder;

    public LocalDate getExpectedFirst() {
        return expectedAdFirst;
    }

    public LocalDate getExpectedAsSecond() {
        return expectedAsSecond;
    }

    public boolean isIdValidOrder() {
        return idValidOrder;
    }

    public DatesOrderValidationResult(LocalDate expectedAsFirst, LocalDate expectedAsSecond, boolean idValidOrder) {
        this.expectedAdFirst = expectedAsFirst;
        this.expectedAsSecond = expectedAsSecond;
        this.idValidOrder = idValidOrder;
    }
}
