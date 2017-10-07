package com.infoshare.core.builders;

import com.infoshare.core.models.bossa.Currency;
import com.infoshare.core.models.bossa.Quotation;

import java.util.ArrayList;
import java.util.List;

public class CurrencyBuilder extends Builder {

    private List<Currency> currencies = new ArrayList<>();

    public int getNumberOfCurrencies() {
        return currencies.size();
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void createCurrenciesFromFile(String filePath) {
        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();
            int id = currencies.size();

            Currency currency = new Currency(id, name, quotationList);
            currencies.add(currency);

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }


}
