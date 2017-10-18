package com.infoshare.mfinance.core.analyzer.analyses.indicator;

import com.infoshare.mfinance.core.analyzer.analyses.Analysis;
import com.infoshare.mfinance.core.analyzer.analyses.IResult;
import com.infoshare.mfinance.core.models.analyses.criteria.IndicatorCriteria;
import com.infoshare.mfinance.core.models.analyses.results.IndicatorResult;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;

import com.infoshare.mfinance.core.models.bossa.Investment;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class Indicator extends Analysis implements IResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(Indicator.class);
    private IndicatorCriteria indicatorCriteria;


    public Indicator(DataContainer dataContainer, IndicatorCriteria indicatorCriteria) {
        this.dataContainer = dataContainer;
        this.indicatorCriteria = indicatorCriteria;
    }

    @Override
    public IndicatorResult getResult() throws NoDataForCriteria {
        try {
            Investment filteredInvestment = this.getInvestment();
            List<Quotation> quotations = this.getQuotations(filteredInvestment);

            IndicatorResult result = new IndicatorResult();
            result.setName(this.getMaxValue(quotations).getName());
            result.setMaxValueQuotation(this.getMaxValue(quotations));
            result.setMinValueQuotation(this.getMinValue(quotations));
            result.setMaxDeltaPlus(this.getMaxDeltaPlusValue(quotations));
            result.setMaxDeltaMinus(this.getMaxDeltaMinusValue(quotations));
            result.setFirstQuotation(this.getFirst(quotations));
            result.setLastQuotation(this.getLast(quotations));
            return result;

        } catch (NoDataForCriteria exception) {
            LOGGER.error("Failed to calculate Investment indicators.{}", exception.getMessage());
            throw exception;
        }
    }

    private Investment getInvestment() throws NoDataForCriteria {

        return dataContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(indicatorCriteria.getName()))
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
        if(quotation.isPresent()) {
            return quotation.get();
        } else{
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
                .reduce((first, second) -> first)
                .orElseThrow(NoDataForCriteria::new);
    }
}
