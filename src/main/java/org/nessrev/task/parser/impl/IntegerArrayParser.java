package org.nessrev.task.parser.impl;

import org.nessrev.task.parser.realization.AbstractNumericParser;

public class IntegerArrayParser extends AbstractNumericParser<Integer> {
  private static final String CHECK_INTEGER = "-?\\d+";

  @Override
  public boolean canParse(String value) {
    return value.matches(CHECK_INTEGER);
  }

  @Override
  protected Integer convert(String value) {
    return Integer.parseInt(value);
  }
}
