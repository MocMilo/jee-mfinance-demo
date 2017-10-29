package com.infoshare.mfinance.core.builders.quotation;

import com.infoshare.mfinance.core.models.bossa.Quotation;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class QuotationBuilder {


    public List<Quotation> getQuotations(String filePath){
        QuotationPartialBuilder quotationData = new QuotationPartialBuilder();
        quotationData.parseQuotationsFromFile(filePath);

        List<Quotation> quotations = new ArrayList<>();
        for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
            quotations.add(quotationData.getQuotation(i));
        }

        this.deltaValueOfClose(quotations);
        return quotations;
    }

    public List<Quotation> getQuotations(InputStream stream){
        QuotationPartialBuilder quotationData = new QuotationPartialBuilder();
        quotationData.parseQuotationsFromStream(stream);

        List<Quotation> quotations = new ArrayList<>();
        for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
            quotations.add(quotationData.getQuotation(i));
        }

        this.deltaValueOfClose(quotations);
        return quotations;
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
