package de.autoscout24.listingreport.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateConverter extends AbstractBeanField {
    @Override
    protected LocalDate convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return Instant.ofEpochMilli(Long.valueOf(s)).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
