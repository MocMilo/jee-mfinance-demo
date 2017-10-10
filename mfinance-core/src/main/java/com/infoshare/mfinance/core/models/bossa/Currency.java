package com.infoshare.mfinance.core.models.bossa;

import java.util.List;

public class Currency extends Investment {

    public Currency(String name, List<Quotation> quotations) {
        super(name, quotations);
    }

}

