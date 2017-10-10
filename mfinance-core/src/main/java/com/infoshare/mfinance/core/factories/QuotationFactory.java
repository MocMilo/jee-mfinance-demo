package com.infoshare.mfinance.core.factories;

import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class QuotationFactory {

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private List<Quotation> quotations = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(QuotationFactory.class);

    public int getNumberOfQuotations() {
        return quotations.size();
    }

    public Quotation getQuotation(int i) {
        return quotations.get(i);
    }

    public void loadDataFromFile(String filepath) {
        try {
            Scanner scanner = new Scanner(new FileReader(filepath));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String data[] = scanner.nextLine().split(",");
                String name = data[0];
                LocalDate date = LocalDate.parse(data[1], formatter);
                BigDecimal close = new BigDecimal(data[5]);

                Quotation quotation = new Quotation(name, date, close);
                quotations.add(quotation);
            }
            scanner.close();

        } catch (Exception e) {
            LOGGER.error("Failded to parse csv file {}, {}", filepath, e.getMessage());
        }
    }
}
