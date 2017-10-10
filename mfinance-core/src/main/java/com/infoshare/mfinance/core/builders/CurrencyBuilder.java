package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.Currency;
import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class CurrencyBuilder extends InvestmentBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyBuilder.class);
    private List<Currency> currencies = new ArrayList<>();

    int getNumberOfCurrencies() {
        return currencies.size();
    }
    List<Currency> getCurrencies() {
        return currencies;
    }

    void createCurrenciesFromFile(String filePath) {

        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();

            currencies.add(new Currency(name, quotationList));

        } catch (Exception e) {
            LOGGER.error("Failed to build Currency:{}", e.getMessage());
        }
    }


}
