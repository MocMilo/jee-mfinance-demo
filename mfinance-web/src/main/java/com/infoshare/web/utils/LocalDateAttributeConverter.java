
package com.infoshare.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateAttributeConverter.class);

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
   // LOGGER.info("converted LocalDate to DatabaseColumn {}",locDate);
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {

     //   LOGGER.info("converted to entity Attribute date {}",sqlDate);
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}

