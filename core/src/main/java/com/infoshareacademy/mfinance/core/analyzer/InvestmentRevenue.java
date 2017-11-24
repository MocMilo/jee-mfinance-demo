package com.infoshareacademy.mfinance.core.analyzer;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import com.infoshareacademy.mfinance.core.models.analyzer.results.InvestmentRevenueResult;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;

import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.math.RoundingMode.HALF_EVEN;

public class InvestmentRevenue extends Analysis implements IResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentRevenue.class);
    private InvestmentRevenueCriteria inputCriteria;



    public InvestmentRevenue(DataContainer dataContainer, InvestmentRevenueCriteria inputCriteria) {
        this.dataContainer = dataContainer;
        this.inputCriteria = inputCriteria;
    }

    public InvestmentRevenueResult getResult() throws NoDataForCriteria {

        try {
            Investment filteredInvestment = getInvestment();

            List<Quotation> quotations = getQuotations(filteredInvestment);

            Quotation buyQuot = getBuyQuotation(quotations);
            Quotation sellQuot = getSellQuotation(quotations);
            checkQuotationOrder(buyQuot, sellQuot);

            return getInvestmentRevenueResult(buyQuot, sellQuot);

        } catch (NoDataForCriteria exception) {
            LOGGER.error("InvestmentRevelnue failure.{}", exception.getMessage());
            throw exception;
        }
    }

    private Investment getInvestment() throws NoDataForCriteria {
        return dataContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(inputCriteria.getInvestmentName()))
                .findFirst().orElseThrow(NoDataForCriteria::new);
    }

    private List<Quotation> getQuotations(Investment filteredInvestment) throws NoDataForCriteria {
        return Optional.ofNullable(filteredInvestment.getQuotations())
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new NoDataForCriteria("No Quotations for current Investment."));
    }

    private Quotation getBuyQuotation(List<Quotation> quotations) throws NoDataForCriteria {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getBuyDate()))
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getSellQuotation(List<Quotation> quotations) throws NoDataForCriteria {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getSellDate()))
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private void checkQuotationOrder(Quotation buyQuot, Quotation sellQuot) throws NoDataForCriteria {

        if (sellQuot.getDate().isBefore(buyQuot.getDate())) {
            throw new NoDataForCriteria("Wrong input data. Quotation BuyDate must be before Quotation SellDate.");
        }
    }

    private InvestmentRevenueResult getInvestmentRevenueResult(Quotation buyQuot, Quotation sellQuot) throws NoDataForCriteria {
        if (buyQuot != null && sellQuot != null) {

            BigDecimal deltaPrice = ((sellQuot.getClose()
                    .subtract(buyQuot.getClose()))
                    .divide(buyQuot.getClose(), 6, HALF_EVEN))
                    .multiply(new BigDecimal(100.0));

            BigDecimal deltaPriceRounded = deltaPrice.setScale(4, HALF_EVEN);

            BigDecimal revenueValue = ((deltaPriceRounded
                    .divide(new BigDecimal(100.0), 2, HALF_EVEN))
                    .multiply(inputCriteria.getInvestedCapital()))
                    .setScale(2, HALF_EVEN);

            return new InvestmentRevenueResult(revenueValue, deltaPriceRounded);

        } else {
            throw new NoDataForCriteria("Failed to calculate InvestmentRevenue.");
        }
    }



}
