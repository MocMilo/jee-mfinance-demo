package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class CurrencyBuilder extends InvestmentBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyBuilder.class);
    private List<InvestmentCurrency> currencies = new ArrayList<>();

    int getNumberOfCurrencies() {
        return currencies.size();
    }
    List<InvestmentCurrency> getCurrencies() {
        return currencies;
    }

    void createCurrenciesFromFile(String filePath) {

        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();

            currencies.add(new InvestmentCurrency(name, quotationList));

        } catch (Exception e) {
            LOGGER.error("Failed to build InvestmentCurrency form file:{}", e.getMessage());
        }
    }

    void createCurrenciesFromStream(InputStream stream) {

        try {
            List<Quotation> quotationList = this.getQuotationsListFromStream(stream);
            String name = quotationList.get(0).getName();

            currencies.add(new InvestmentCurrency(name, quotationList));

        } catch (Exception e) {
            LOGGER.error("Failed to build InvestmentCurrency form stream:{}", e.getMessage());
        }
    }
}
