package org.nessrev.service.calculation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nessrev.entity.NumericArrayEntity;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationServiceImplTest {
    private static final Integer[] INTEGER_ARRAY = {1, 2, 3};
    private static final Integer[] NEGATIVE_ARRAY = {-5, -7, -3};
    private static final Integer[] EMPTY_ARRAY = {};
    private static final Integer[] ONE_DIGIT = {2};

    private static final NumericArrayEntity<Integer> INTEGER_ENTITY =
            new NumericArrayEntity<>(INTEGER_ARRAY);

    private static final NumericArrayEntity<Integer> NEGATIVE_ENTITY =
            new NumericArrayEntity<>(NEGATIVE_ARRAY);

    private static final NumericArrayEntity<Integer> EMPTY_ENTITY =
            new NumericArrayEntity<>(EMPTY_ARRAY);

    private static final NumericArrayEntity<Integer> ENTITY_WITH_ONE_DIGIT =
            new NumericArrayEntity<>(ONE_DIGIT);

    private final CalculationServiceImpl calculationService =
            new CalculationServiceImpl();

    @ParameterizedTest
    @MethodSource("minCases")
    void min_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
        Optional<Double> actual = calculationService.min(entity);

        assertEquals(Optional.ofNullable(expected), actual);
    }

    private static Stream<Arguments> minCases() {
        return Stream.of(
                Arguments.of(INTEGER_ENTITY, 1.0),
                Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
                Arguments.of(NEGATIVE_ENTITY, -7.0),
                Arguments.of(EMPTY_ENTITY, null)
        );
    }

    @ParameterizedTest
    @MethodSource("maxCases")
    void max_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
        Optional<Double> actual = calculationService.max(entity);

        assertEquals(Optional.ofNullable(expected), actual);
    }

    private static Stream<Arguments> maxCases() {
        return Stream.of(
                Arguments.of(INTEGER_ENTITY, 3.0),
                Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
                Arguments.of(NEGATIVE_ENTITY, -3.0),
                Arguments.of(EMPTY_ENTITY, null)
        );
    }

    @ParameterizedTest
    @MethodSource("sumCases")
    void sum_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
        Optional<Double> actual = calculationService.sum(entity);

        assertEquals(Optional.ofNullable(expected), actual);
    }

    private static Stream<Arguments> sumCases() {
        return Stream.of(
                Arguments.of(INTEGER_ENTITY, 6.0),
                Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
                Arguments.of(NEGATIVE_ENTITY, -15.0),
                Arguments.of(EMPTY_ENTITY, null)
        );
    }

    @ParameterizedTest
    @MethodSource("avgCases")
    void avg_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
        Optional<Double> actual = calculationService.avg(entity);

        assertEquals(Optional.ofNullable(expected), actual);
    }

    private static Stream<Arguments> avgCases() {
        return Stream.of(
                Arguments.of(INTEGER_ENTITY, 2.0),
                Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
                Arguments.of(NEGATIVE_ENTITY, -5.0),
                Arguments.of(EMPTY_ENTITY, null)
        );
    }

}
