package org.nessrev.task.parser.general;

public interface GeneralParser<T extends Number> {
    boolean canParse(String value);
    T parse(String value);
}