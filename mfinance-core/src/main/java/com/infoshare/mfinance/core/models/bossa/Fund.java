package com.infoshare.mfinance.core.models.bossa;

import java.util.List;

public class Fund extends Investment {

    public Fund(long Id, String name, List<Quotation> quotations) {
        super(Id, name, quotations);
    }

}
