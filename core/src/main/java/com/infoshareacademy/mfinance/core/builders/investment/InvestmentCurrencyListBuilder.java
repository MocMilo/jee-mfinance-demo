package com.infoshareacademy.mfinance.core.builders.investment;

import com.infoshareacademy.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InvestmentCurrencyListBuilder extends InvestmentListBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentCurrencyListBuilder.class);
    private List<InvestmentCurrency> currencies = new ArrayList<>();

    public int getNumberOfCurrencies() {
        return currencies.size();
    }

    public List<InvestmentCurrency> getCurrencies() {
        return currencies;
    }

    public void createCurrenciesFromFile(String filePath) {

        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();

            currencies.add(new InvestmentCurrency(name, quotationList));

        } catch (Exception e) {
            LOGGER.error("Failed to build InvestmentCurrency form locations:{}", e.getMessage());
        }
    }

}
