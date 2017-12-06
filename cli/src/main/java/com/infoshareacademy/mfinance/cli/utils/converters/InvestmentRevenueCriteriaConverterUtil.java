package com.infoshareacademy.mfinance.cli.utils.converters;

import com.infoshareacademy.mfinance.core.models.analyzer.criteria.InvestmentRevenueCriteria;
import com.infoshareacademy.mfinance.cli.model.arguments.IVRArgs;
import com.infoshareacademy.mfinance.core.utils.BigDecimalUtil;
import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvestmentRevenueCriteriaConverterUtil {
    private static ModelMapper modelMapper = new ModelMapper();

    public static InvestmentRevenueCriteria convertFrom(IVRArgs ivrArgs) {

        Converter<String, LocalDate> stringToLocalDate =
                c -> c.getSource() == null ? null : LocalDateUtil.parseForm(c.getSource());

        Converter<String, BigDecimal> stringToBigDecimal =
                c -> c.getSource() == null ? null : BigDecimalUtil.parseFormMoney(c.getSource());

        TypeMap<IVRArgs, InvestmentRevenueCriteria> typeMap = modelMapper.createTypeMap(IVRArgs.class, InvestmentRevenueCriteria.class);
        typeMap.addMappings(mapper -> mapper.using(stringToBigDecimal).map(IVRArgs::getCapital, InvestmentRevenueCriteria::setInvestedCapital));
        typeMap.addMappings(mapper -> mapper.using(stringToLocalDate).map(IVRArgs::getStartDate, InvestmentRevenueCriteria::setBuyDate));
        typeMap.addMappings(mapper -> mapper.using(stringToLocalDate).map(IVRArgs::getEndDate, InvestmentRevenueCriteria::setSellDate));
        return modelMapper.map(ivrArgs, InvestmentRevenueCriteria.class);
    }
}
