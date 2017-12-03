package com.infoshareacademy.mfinance.core.analyzer;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.IndicatorCriteria;
import com.infoshareacademy.mfinance.core.models.analyzer.results.IndicatorResult;
import com.infoshareacademy.mfinance.core.models.exceptions.NoDataForCriteria;

import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.bossa.Quotation;

import java.math.BigDecimal;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class InvestmentIndicator extends Analysis implements IResult {
    private IndicatorCriteria indicatorCriteria;

    public InvestmentIndicator(DataContainer dataContainer, IndicatorCriteria indicatorCriteria) {
        this.dataContainer = dataContainer;
        this.indicatorCriteria = indicatorCriteria;
    }

    @Override
    public IndicatorResult getResult() throws NoDataForCriteria {
        Investment filteredInvestment = this.getInvestment();
        List<Quotation> quotations = this.getQuotations(filteredInvestment);

        IndicatorResult result = new IndicatorResult();
        result.setName(getMaxValue(quotations).getName());
        result.setMaxValueQuotation(getMaxValue(quotations));
        result.setMinValueQuotation(getMinValue(quotations));
        result.setMaxDeltaPlus(getMaxDeltaPlusValue(quotations));
        result.setMaxDeltaMinus(getMaxDeltaMinusValue(quotations));
        result.setFirstQuotation(getFirst(quotations));
        result.setLastQuotation(getLast(quotations));
        return result;
    }

    private Investment getInvestment() throws NoDataForCriteria {
        return dataContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(indicatorCriteria.getInvestmentName()))
                .findFirst().orElseThrow(NoDataForCriteria::new);
    }

    private List<Quotation> getQuotations(Investment filteredInvestment) throws NoDataForCriteria {
        return Optional.ofNullable(filteredInvestment.getQuotations())
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new NoDataForCriteria("No Quotations for current Investment."));
    }

    private Quotation getMaxValue(List<Quotation> quotations) throws NoDataForCriteria {
        BigDecimal maxCloseValue = quotations.stream()
                .map(Quotation::getClose)
                .max(BigDecimal::compareTo)
                .orElseThrow(NoDataForCriteria::new);

        Predicate<Quotation> maxValuePredicate = x -> Objects.equals(x.getClose(), maxCloseValue);
        Optional<Quotation> quotation = quotations.stream()
                .filter(maxValuePredicate)
                .findAny();

        if (quotation.isPresent()) {
            return quotation.get();
        } else {
            throw new NoDataForCriteria();
        }
    }

    private Quotation getMinValue(List<Quotation> quotations) throws NoDataForCriteria {
        BigDecimal minCloseValue = quotations.stream()
                .map(Quotation::getClose)
                .min(BigDecimal::compareTo)
                .orElseThrow(NoDataForCriteria::new);

        Predicate<Quotation> minValuePredicate = x -> Objects.equals(x.getClose(), minCloseValue);
        Optional<Quotation> quotation = quotations.stream()
                .filter(minValuePredicate)
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getMaxDeltaPlusValue(List<Quotation> quotations) throws NoDataForCriteria {
        BigDecimal maxDeltaPlus = quotations.stream()
                .map(Quotation::getDeltaClose)
                .max(BigDecimal::compareTo)
                .orElseThrow(NoDataForCriteria::new);

        Predicate<Quotation> maxDeltaPlusValuePredicate = x -> Objects.equals(x.getDeltaClose(), maxDeltaPlus);
        Optional<Quotation> quotation = quotations.stream()
                .filter(maxDeltaPlusValuePredicate)
                .findFirst();

        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getMaxDeltaMinusValue(List<Quotation> quotations) throws NoDataForCriteria {
        BigDecimal maxDeltaMinus = quotations.stream()
                .map(Quotation::getDeltaClose)
                .min(BigDecimal::compareTo)
                .orElseThrow(NoDataForCriteria::new);

        Predicate<Quotation> maxDeltaMinusValuePredicate = x -> Objects.equals(x.getDeltaClose(), maxDeltaMinus);
        Optional<Quotation> quotation = quotations.stream()
                .filter(maxDeltaMinusValuePredicate)
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getFirst(List<Quotation> quotations) throws NoDataForCriteria {
        Optional<Quotation> quotation = quotations.stream()
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getLast(List<Quotation> quotations) throws NoDataForCriteria {
        return quotations.stream()
                .sorted()
                .reduce((first, second) -> second)
                .orElseThrow(NoDataForCriteria::new);
    }
}
