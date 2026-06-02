package org.nessrev.service.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerArrayParserTest {
    private final IntegerArrayParser parser = new IntegerArrayParser();

    @ParameterizedTest
    @MethodSource("canParseCases")
    void canParse_shouldReturnExpected(String input, boolean expected) {

        boolean result = parser.canParse(input);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> canParseCases() {
        return Stream.of(
                Arguments.of("1", true),
                Arguments.of("-1", true),
                Arguments.of("123", true),
                Arguments.of("0", true),

                Arguments.of("1.0", false),
                Arguments.of("abc", false),
                Arguments.of("1,5", false),
                Arguments.of("", false),
                Arguments.of("1 2", false),
                Arguments.of("12.34", false)
        );
    }

    @Test
    void convert_shouldReturnIntegerValue() {
        Integer result = parser.parse("12");

        assertEquals(12, result);
    }

    @ParameterizedTest
    @MethodSource("parseCases")
    void parse_shouldReturnIntegerValue(String input, Integer expected) {
        Integer result = parser.parse(input);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> parseCases() {
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("-1", -1),
                Arguments.of("123", 123),
                Arguments.of("0", 0)
        );
    }
}
