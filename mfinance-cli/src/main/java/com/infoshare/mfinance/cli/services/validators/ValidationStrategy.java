package com.infoshare.mfinance.cli.services.validators;


import com.infoshare.mfinance.cli.services.parser.ParserResult;

public interface ValidationStrategy {
   ParserResult validate(String[] args);
}
