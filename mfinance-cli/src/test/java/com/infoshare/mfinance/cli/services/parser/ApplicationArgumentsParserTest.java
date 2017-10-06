package com.infoshare.mfinance.cli.services.parser;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApplicationArgumentsParserTest {

   private ApplicationArgumentsParser parser = new ApplicationArgumentsParser();
   private String[] args = {"IVR"};

   // TODO no arguments - no analysis name test case (eg. no "IVR", "IND"... etc.)

   @Test
   public void noAnalysisArgumentsParsingValidation(){

       ParserResult result = parser.parse(args);
       assertThat(result.isValid(), is(equalTo(false)) );
   }

}