package org.nessrev.task.parser.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.parser.GeneralParser;

import java.util.List;
import java.util.Optional;

public class ParserDispatcher {
  private static final Logger logger = LogManager.getLogger();
  private final List<GeneralParser<? extends Number>> parsers;

  public ParserDispatcher(List<GeneralParser<? extends Number>> parsers) {
    this.parsers = parsers;
  }

  public Optional<Number> parse(String value) {
    for (GeneralParser<? extends Number> parser : parsers) {
      if (parser.canParse(value)) {
        return Optional.ofNullable(parser.parse(value));
      }
    }
    logger.warn("Unknown format: {}", value);
    return Optional.empty();
  }
}
