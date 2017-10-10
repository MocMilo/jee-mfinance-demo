package com.infoshare.mfinance.core.models.bossa;

import java.util.List;

public class Fund extends Investment {

    public Fund(String name, List<Quotation> quotations) {
        super(name, quotations);
    }

}
