package org.nessrev.service.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumericArrayValidatorImplTest {
    private final NumericArrayValidatorImpl validator =
            new NumericArrayValidatorImpl();

    @ParameterizedTest
    @MethodSource("cases")
    void validate_shouldReturnCorrectResult(List<String> input, List<String> expected) {
        List<String> result = validator.validateNumericArray(input);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(
                        List.of("1 abc 2", "3, 4; test 5"),
                        List.of("1", "2", "3", "4", "5")
                ),
                Arguments.of(
                        List.of("dhg igo p", "y7 oh9hok huh"),
                        List.of()
                ),
                Arguments.of(
                        List.of("1, 2, 3; 4", "5 - 6 + 7"),
                        List.of("1", "2", "3", "4", "5", "6", "7")
                ),
                Arguments.of(
                        List.of(),
                        List.of()
                ),
                Arguments.of(
                        List.of("2.45, 7.89", "5.86 2.08"),
                        List.of("2.45", "7.89", "5.86", "2.08")
                )
        );
    }

}
