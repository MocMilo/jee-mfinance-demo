package com.infoshareacademy.mfinance.core.serialization;

import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import com.infoshareacademy.mfinance.core.utils.BigDecimalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotationListCSVParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotationListCSVParser.class);
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private final String filepath;


    private List<Quotation> quotations = new ArrayList<>();

    public QuotationListCSVParser(String filepath) {
        this.filepath = filepath;
    }

    public int getNumberOfQuotations() {
        return quotations.size();
    }

    public Quotation getQuotation(int i) {
        return quotations.get(i);
    }

    public void parseQuotationsFromFile() {

        try(FileReader fileReader = new FileReader(filepath);
            Scanner scanner = new Scanner(fileReader))  {

            this.parseQuotations(scanner);

        } catch (Exception e) {
            LOGGER.error("Failed to parse quotations, locations:{}, {}", filepath, e.getMessage());
            throw new RuntimeException("Failed to parse quotations, locations:" + filepath + e.getMessage());
        }
    }

    private void parseQuotations(Scanner scanner) {

        scanner.nextLine();

        while (scanner.hasNextLine()) {

            String data[] = scanner.nextLine().split(",");
            String name = data[0];
            LocalDate date = LocalDate.parse(data[1], FORMATTER);

            BigDecimal close = BigDecimalUtil.parseExchangeRate(data[5]);

            quotations.add(new Quotation(name, date, close));
        }
    }
}

