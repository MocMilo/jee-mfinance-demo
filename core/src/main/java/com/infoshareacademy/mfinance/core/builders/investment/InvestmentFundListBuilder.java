package com.infoshareacademy.mfinance.core.builders.investment;

import com.infoshareacademy.mfinance.core.models.bossa.InvestmentFund;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

public class InvestmentFundListBuilder extends InvestmentListBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentFundListBuilder.class);
    private List<InvestmentFund> investmentFunds = new ArrayList<>();

    public int getNumberOfFunds() {
        return investmentFunds.size();
    }

    public List<InvestmentFund> getInvestmentFunds() {
        return investmentFunds;
    }

    public void createFundsFromFile(String filePath) {
        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();

            investmentFunds.add(new InvestmentFund(name, quotationList));

        } catch (Exception e) {
            LOGGER.error("Failed to build InvestmentFund:{}", e.getMessage());
        }
    }
}
