package com.infoshare.mfinance.core.builders.quotation;

import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.math.RoundingMode.HALF_EVEN;

public class QuotationPartialBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotationPartialBuilder.class);
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private Scanner scanner;
    private List<Quotation> quotations = new ArrayList<>();

    public int getNumberOfQuotations() {
        return quotations.size();
    }

    public Quotation getQuotation(int i) {
        return quotations.get(i);
    }

    public void parseQuotationsFromFile(String filepath) {

        try {
            FileReader fileReader = new FileReader(filepath);
            scanner = new Scanner(fileReader);
            parseQuotations(scanner);

        } catch (Exception e) {
            LOGGER.error("Failed to parse quotations, file:{}, {}", filepath, e.getMessage());
            throw new RuntimeException("Failed to parse quotations, file:" + filepath + e.getMessage());
        }
    }

    public void parseQuotationsFromStream(InputStream stream) {

        try {
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            scanner = new Scanner(reader);
            parseQuotations(scanner);

        } catch (Exception e) {
            LOGGER.error("Failed to parse quotations from stream, {}", e.getMessage());
            throw new RuntimeException("Failed to parse quotations from stream:" + e.getMessage());
        }
    }

    private void parseQuotations(Scanner scanner) {
        scanner.nextLine();

        while (scanner.hasNextLine()) {

            String data[] = scanner.nextLine().split(",");
            String name = data[0];
            LocalDate date = LocalDate.parse(data[1], FORMATTER);
            BigDecimal close = new BigDecimal(data[5]).setScale(4, HALF_EVEN);

            quotations.add(new Quotation(name, date, close));
        }
        scanner.close();

    }
}

