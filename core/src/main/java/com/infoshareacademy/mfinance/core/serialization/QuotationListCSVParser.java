package com.infoshareacademy.mfinance.core.serialization;

import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import com.infoshareacademy.mfinance.core.utils.BigDecimalUtil;
import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuotationListCSVParser {
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

    public void parseQuotationsFromFile() throws IOException {
        try (FileReader fileReader = new FileReader(filepath);
             Scanner scanner = new Scanner(fileReader)) {
            this.parseQuotations(scanner);
        }
    }

    private void parseQuotations(Scanner scanner) {
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String data[] = scanner.nextLine().split(",");
            String name = data[0];
            LocalDate date = LocalDateUtil.parseCSV(data[1]);
            BigDecimal close = BigDecimalUtil.parseCSV(data[5]);
            quotations.add(new Quotation(name, date, close));
        }
    }
}

