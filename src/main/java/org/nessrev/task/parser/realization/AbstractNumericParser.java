package org.nessrev.service.parser.realization;

import org.nessrev.service.parser.general.GeneralParser;

public abstract class AbstractNumericParser<T extends Number> implements GeneralParser<T> {
    @Override
    public T parse(String value) {
        return convert(value);
    }

    protected abstract T convert(String value);
}