package com.infoshareacademy.mfinance.core.builders.quotation;

import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import com.infoshareacademy.mfinance.core.serialization.QuotationListCSVParser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class QuotationListBuilder {

    private final String filePath;

    public List<Quotation> getQuotations() {
        QuotationListCSVParser quotationData = new QuotationListCSVParser(filePath);
        quotationData.parseQuotationsFromFile();

        List<Quotation> quotations = new ArrayList<>();
        for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
            quotations.add(quotationData.getQuotation(i));
        }

        this.deltaValueOfClose(quotations);
        return quotations;
    }

    public QuotationListBuilder(String filePath) {
        this.filePath = filePath;
    }

    private void deltaValueOfClose(List<Quotation> quotations) {
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
