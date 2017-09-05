package com.infoshare.core.model;

import java.util.List;

public class Fund extends Investment {

    public Fund(int Id, String name, List<Quotation> quotations) {
        super(Id, name, quotations);
    }

}
