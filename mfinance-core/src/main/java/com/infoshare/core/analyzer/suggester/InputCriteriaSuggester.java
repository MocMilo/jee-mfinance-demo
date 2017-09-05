package com.infoshare.core.analyzer.suggester;

import com.infoshare.core.analyzer.analyses.exception.NoDataForCriteria;
import com.infoshare.core.model.Quotation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InputCriteriaSuggester {
    private List<LocalDate> dateList = new ArrayList<>();

    public Optional<Quotation> getNearestQuotation(List<Quotation> quotations, LocalDate targetDate) throws NoDataForCriteria {

        if (quotations != null && !quotations.isEmpty()) {
            for (Quotation quotation : quotations) {
                dateList.add(quotation.getDate());
            }
        }
        LocalDate nearestDate = this.getNearestPreviousDate(dateList, targetDate);

        if(quotations!=null && !quotations.isEmpty()) {
        return quotations.stream()
               .filter(x -> x.getDate().equals(nearestDate))
               .limit(1)
               .findFirst();
        }
      throw new NoDataForCriteria("Failed to propose near possible date by suggester. Null or empty Quotation list.");
    }

    private LocalDate getNearestPreviousDate(List<LocalDate> dates, LocalDate targetDate) throws NoDataForCriteria {
       TreeSet<LocalDate> set = new TreeSet<>(dates);
        if(set.lower(targetDate)!=null) {
            return set.lower(targetDate);
        }
        else {
            throw new NoDataForCriteria("Suggester failed to propose nearest (previous) possible date.");
        }
    }

}
