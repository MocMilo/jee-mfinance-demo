package com.infoshareacademy.mfinance.cli.services.parser.strategies;

import com.infoshareacademy.mfinance.cli.model.ParserResult;

public interface ValidationStrategy {
   ParserResult validate(String[] args);
}
