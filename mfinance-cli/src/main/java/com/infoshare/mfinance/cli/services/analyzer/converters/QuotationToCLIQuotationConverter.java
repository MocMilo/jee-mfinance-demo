package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.core.models.bossa.Quotation;
import com.infoshare.mfinance.cli.model.results.embeded.CLIQuotation;
import org.modelmapper.ModelMapper;


public class QuotationToCLIQuotationConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public CLIQuotation convertFrom(Quotation quotation) {

        return modelMapper.map(quotation, CLIQuotation.class);
    }
}
