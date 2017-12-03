package com.infoshareacademy.mfinance.core.builders.investment;

import com.infoshareacademy.mfinance.core.models.bossa.InvestmentCurrency;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvestmentCurrencyListBuilder extends InvestmentListBuilder {
    private List<InvestmentCurrency> currencies = new ArrayList<>();

    public int getNumberOfCurrencies() {
        return currencies.size();
    }

    public List<InvestmentCurrency> getCurrencies() {
        return currencies;
    }

    public void createCurrenciesFromFile(String filePath) throws IOException {
        List<Quotation> quotationList = this.getQuotationsList(filePath);
        String name = quotationList.get(0).getName();
        currencies.add(new InvestmentCurrency(name, quotationList));
    }
}
