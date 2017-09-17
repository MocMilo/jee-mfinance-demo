package com.infoshare.core.models.bossa;

import java.util.List;

public class Currency extends Investment {

    public Currency(int Id, String name, List<Quotation> quotations) {
        super(Id, name, quotations);
    }

}

