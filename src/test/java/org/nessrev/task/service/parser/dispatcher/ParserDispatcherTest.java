package org.nessrev.task.service.parser.dispatcher;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nessrev.task.parser.GeneralParser;
import org.nessrev.task.parser.dispatcher.ParserDispatcher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParserDispatcherTest {

  private static Stream<Arguments> cases() {
    return Stream.of(
      Arguments.of("123", 123, true),
      Arguments.of("abc", null, false)
    );
  }

  @SuppressWarnings("unchecked")
  @ParameterizedTest
  @MethodSource("cases")
  void parse_shouldReturnExpected(String input, Number expected, boolean canParse) {
    GeneralParser<Number> parser = mock(GeneralParser.class);
    when(parser.canParse(input)).thenReturn(canParse);
    if (canParse && expected != null) {
      when(parser.parse(input)).thenReturn(expected);
    }
    ParserDispatcher dispatcher =
      new ParserDispatcher(List.of(parser));

    Optional<Number> result = dispatcher.parse(input);

    assertEquals(Optional.ofNullable(expected), result);
  }
}
