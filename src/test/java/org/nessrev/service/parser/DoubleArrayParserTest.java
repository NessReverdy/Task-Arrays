package org.nessrev.service.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleArrayParserTest {
    private final DoubleArrayParser parser = new DoubleArrayParser();

    @ParameterizedTest
    @MethodSource("canParseCases")
    void canParse_shouldReturnExpected(String input, boolean expected) {
        boolean result = parser.canParse(input);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> canParseCases() {
        return Stream.of(
                Arguments.of("1.0", true),
                Arguments.of("-1.5", true),
                Arguments.of("0.123", true),
                Arguments.of("10.99", true),

                Arguments.of("1", false),
                Arguments.of("abc", false),
                Arguments.of("1,5", false),
                Arguments.of("", false),
                Arguments.of("1.2.3", false)
        );
    }


    @Test
    void convert_shouldReturnDoubleValue() {
        Double result = parser.parse("12.34");

        assertEquals(12.34, result);
    }

    @ParameterizedTest
    @MethodSource("parseCases")
    void parse_shouldReturnIntegerValue(String input, Double expected) {
        Double result = parser.parse(input);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> parseCases() {
        return Stream.of(
                Arguments.of("1.8", 1.8),
                Arguments.of("-1.09", -1.09),
                Arguments.of("12.3", 12.3),
                Arguments.of("0", 0.0)
        );
    }
}
