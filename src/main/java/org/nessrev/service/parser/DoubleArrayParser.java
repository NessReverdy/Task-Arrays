package org.nessrev.service.parser;

import org.nessrev.service.parser.realization.AbstractNumericParser;

public class DoubleArrayParser extends AbstractNumericParser<Double> {
    private static final String CHECK_DOUBLE = "-?\\d+\\.\\d+";

    @Override
    public boolean canParse(String value) {
        return value.matches(CHECK_DOUBLE);
    }

    @Override
    protected Double convert(String value) {
        return Double.parseDouble(value);
    }
}
