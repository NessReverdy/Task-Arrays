package org.nessrev.task.parser.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.parser.GeneralParser;

import java.util.List;

public class ParserDispatcher {
  private final List<GeneralParser<? extends Number>> parsers;
  private static final Logger logger = LogManager.getLogger(ParserDispatcher.class);

  public ParserDispatcher(List<GeneralParser<? extends Number>> parsers) {
    this.parsers = parsers;
  }

  public Number parse(String value) {
    for (GeneralParser<? extends Number> parser : parsers) {
      if (parser.canParse(value)) {
        return parser.parse(value);
      }
    }
    logger.warn("Unknown format: {}", value);
    return null;
  }
}
