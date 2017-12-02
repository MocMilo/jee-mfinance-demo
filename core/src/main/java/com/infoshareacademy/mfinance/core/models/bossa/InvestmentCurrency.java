package com.infoshareacademy.mfinance.core.models.bossa;

import java.util.List;

public class InvestmentCurrency extends Investment {
    public InvestmentCurrency(String name, List<Quotation> quotations) {
        super(name, quotations);
    }
}

