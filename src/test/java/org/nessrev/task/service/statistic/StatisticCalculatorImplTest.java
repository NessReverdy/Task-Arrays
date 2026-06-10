package org.nessrev.task.service.statistic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.service.statistic.impl.StatisticsCalculatorImpl;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticCalculatorImplTest {

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

  private final StatisticsCalculator statisticCalculator =
    new StatisticsCalculatorImpl();

  private static Stream<Arguments> minCases() {
    return Stream.of(
      Arguments.of(INTEGER_ENTITY, 1.0),
      Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
      Arguments.of(NEGATIVE_ENTITY, -7.0)
    );
  }

  private static Stream<Arguments> maxCases() {
    return Stream.of(
      Arguments.of(INTEGER_ENTITY, 3.0),
      Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
      Arguments.of(NEGATIVE_ENTITY, -3.0)
    );
  }

  private static Stream<Arguments> sumCases() {
    return Stream.of(
      Arguments.of(INTEGER_ENTITY, 6.0),
      Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
      Arguments.of(NEGATIVE_ENTITY, -15.0)
    );
  }

  private static Stream<Arguments> avgCases() {
    return Stream.of(
      Arguments.of(INTEGER_ENTITY, 2.0),
      Arguments.of(ENTITY_WITH_ONE_DIGIT, 2.0),
      Arguments.of(NEGATIVE_ENTITY, -5.0)
    );
  }

  @ParameterizedTest
  @MethodSource("minCases")
  void min_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
    assertEquals(expected, statisticCalculator.min(entity));
  }

  @ParameterizedTest
  @MethodSource("maxCases")
  void max_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
    assertEquals(expected, statisticCalculator.max(entity));
  }

  @ParameterizedTest
  @MethodSource("sumCases")
  void sum_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
    assertEquals(expected, statisticCalculator.sum(entity));
  }

  @ParameterizedTest
  @MethodSource("avgCases")
  void avg_shouldReturnCorrectValue(NumericArrayEntity<Integer> entity, Double expected) {
    assertEquals(expected, statisticCalculator.avg(entity));
  }

  @org.junit.jupiter.api.Test
  void avg_shouldThrowException_forEmptyArray() {
    assertThrows(NoSuchElementException.class,
      () -> statisticCalculator.avg(EMPTY_ENTITY));
  }

  @org.junit.jupiter.api.Test
  void min_shouldThrowException_forEmptyArray() {
    assertThrows(NoSuchElementException.class,
      () -> statisticCalculator.min(EMPTY_ENTITY));
  }

  @org.junit.jupiter.api.Test
  void max_shouldThrowException_forEmptyArray() {
    assertThrows(NoSuchElementException.class,
      () -> statisticCalculator.max(EMPTY_ENTITY));
  }
}