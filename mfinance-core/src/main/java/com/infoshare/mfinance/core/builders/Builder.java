package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.Quotation;
import com.infoshare.mfinance.core.factories.QuotationFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public abstract class Builder {


    protected List<Quotation> getQuotationsList(String filePath) {

        QuotationFactory quotationData = new QuotationFactory();
        quotationData.loadDataFromFile("" + filePath);

        List<Quotation> quotations = new ArrayList<>();
        for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
            quotations.add(quotationData.getQuotation(i));
        }
        this.deltaValueOfClose(quotations);
        return quotations;
    }


    protected void deltaValueOfClose(List<Quotation> quotations) {

        for (Quotation quotation : quotations) {
            if ((quotations.indexOf(quotation) > 0 && quotations.indexOf(quotation) < quotations.size())) {

                BigDecimal previousValue = quotations.get(quotations.indexOf(quotation) - 1).getClose()
                        .setScale(2, RoundingMode.HALF_EVEN);
                BigDecimal actualValue = quotation.getClose()
                        .setScale(2, RoundingMode.HALF_EVEN);

                BigDecimal deltaClose;

                deltaClose = ((actualValue.subtract(previousValue)))
                        .divide(previousValue, 8, RoundingMode.HALF_EVEN)
                        .setScale(4, RoundingMode.HALF_EVEN)
                        .multiply(new BigDecimal(100.0));

                quotation.setDeltaClose(deltaClose);
            } else if (quotations.indexOf(quotation) == 0) {
                quotation.setDeltaClose(new BigDecimal(0.0));
            }
        }
    }
}
