package com.infoshare.mfinance.core.models.bossa;

import java.util.List;

public class InvestmentFund extends Investment {

    public InvestmentFund(String name, List<Quotation> quotations) {
        super(name, quotations);
    }

}
