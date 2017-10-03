package com.infoshare.mfinance.cli.services.parser.strategies;


import com.infoshare.mfinance.cli.services.parser.ParserResult;

public interface ValidationStrategy {
   ParserResult validate(String[] args);
}
