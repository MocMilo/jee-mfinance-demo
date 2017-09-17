package com.infoshare.core.analyzer.analyses.trend;

import com.infoshare.core.analyzer.analyses.Analysis;
import com.infoshare.core.models.analyses.results.AnalysisResult;
import com.infoshare.core.analyzer.analyses.IResult;
import com.infoshare.core.models.analyses.criteria.QuotationSeriesCriteria;
import com.infoshare.core.models.analyses.results.QuotationSeriesResult;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.core.models.bossa.Investment;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.bossa.Quotation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuotationSeries extends Analysis implements IResult {

    private QuotationSeriesCriteria inputCriteria;

    @Override
    public AnalysisResult getResult() throws NoDataForCriteria {

        this.checkDatesOrder(inputCriteria.getStartDate(), inputCriteria.getEndDate());
        Investment investment = getInvestment();
        List<Quotation> quotations = getQuotations(investment);
        List<Quotation> filteredQuotations = getQuotationFromDateRange(quotations);
        return new QuotationSeriesResult(filteredQuotations);
    }

    public QuotationSeries(MainContainer mainContainer, QuotationSeriesCriteria inputCriteria) {
        this.mainContainer = mainContainer;
        this.inputCriteria = inputCriteria;
    }

    private List<Quotation> getQuotationFromDateRange(List<Quotation> quotations) throws NoDataForCriteria {
        List<Quotation> filteredQuotatios = quotations.stream()
                .filter(x -> x.getDate().isEqual(inputCriteria.getStartDate())||x.getDate().isAfter(inputCriteria.getStartDate()))
                .filter(x -> x.getDate().isEqual(inputCriteria.getEndDate())||x.getDate().isBefore(inputCriteria.getEndDate()))
                .collect(Collectors.toList());

        if (filteredQuotatios.size()<2) {
        throw new NoDataForCriteria("No quotations for current dates range or less then 2");
        }
        return filteredQuotatios;
    }

    private void checkDatesOrder(LocalDate startDate, LocalDate endDate) throws NoDataForCriteria {
        if (!startDate.isBefore(endDate)) {
            throw new NoDataForCriteria("Wrong input data. StartDate must be before EndDate.");
        }
    }

    private Investment getInvestment() throws NoDataForCriteria {
        return mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(inputCriteria.getInvestmentName()))
                .findFirst().orElseThrow(NoDataForCriteria::new);
    }

    private List<Quotation> getQuotations(Investment filteredInvestment) throws NoDataForCriteria {
        return Optional.ofNullable(filteredInvestment.getQuotations())
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new NoDataForCriteria("No Quotations for current Investment name."));
    }
}
