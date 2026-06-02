package org.nessrev.task.parser.realization;

import org.nessrev.task.parser.GeneralParser;

public abstract class AbstractNumericParser<T extends Number> implements GeneralParser<T> {
    @Override
    public T parse(String value) {
        return convert(value);
    }

    protected abstract T convert(String value);
}