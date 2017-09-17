package com.infoshare.core.models.bossa;

import java.util.List;

public class Fund extends Investment {

    public Fund(int Id, String name, List<Quotation> quotations) {
        super(Id, name, quotations);
    }

}
