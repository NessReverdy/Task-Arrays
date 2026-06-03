package org.nessrev.task.service.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exception.CustomException;
import org.nessrev.task.service.sort.impl.SortServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortServiceImplTest {
  private static final Integer[] SORTED_ARRAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  private static final Integer[] UNSORTED_ARRAY = {5, 8, -6, 4, 9, 8, -1, 0, 3, 7, -2};
  private static final Integer[] EMPTY_ARRAY = {};
  private static final Integer[] ONE_DIGIT = {2};
  private static final Integer[] SAME_DIGITS = {2, 2, 2, 2, 2};

  private static final SortServiceImpl sortService = new SortServiceImpl();

  private static Stream<Arguments> arraysForQuickSort() {
    return Stream.of(
      Arguments.of(
        ONE_DIGIT,
        new Integer[]{2}
      ),
      Arguments.of(
        SAME_DIGITS,
        new Integer[]{2, 2, 2, 2, 2}
      ),
      Arguments.of(
        UNSORTED_ARRAY,
        new Integer[]{-6, -2, -1, 0, 3, 4, 5, 7, 8, 8, 9}
      ),
      Arguments.of(
        SORTED_ARRAY,
        new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
      )
    );
  }

  private static Stream<Arguments> invalidCases() {
    return Stream.of(
      Arguments.of((NumericArrayEntity<Integer>) null),
      Arguments.of(new NumericArrayEntity<>(null)),
      Arguments.of(new NumericArrayEntity<>(EMPTY_ARRAY))
    );
  }

  @ParameterizedTest
  @MethodSource("arraysForQuickSort")
  void quickSort_shouldSortCorrectly(Integer[] input, Integer[] expected) throws CustomException {
    NumericArrayEntity<Integer> entity =
      new NumericArrayEntity<>(input);

    NumericArrayEntity<Integer> actual =
      sortService.quickSort(entity);

    assertArrayEquals(expected, actual.getNumericArray());
  }

  @ParameterizedTest
  @MethodSource("arraysForQuickSort")
  void mergeSort_shouldSortCorrectly(Integer[] input, Integer[] expected) throws CustomException {
    NumericArrayEntity<Integer> entity =
      new NumericArrayEntity<>(input);

    NumericArrayEntity<Integer> actual =
      sortService.mergeSort(entity);

    assertArrayEquals(expected, actual.getNumericArray());
  }

  @ParameterizedTest
  @MethodSource("invalidCases")
  void quickSort_shouldHandleInvalidInput(NumericArrayEntity<Integer> entity) {
    assertThrows(CustomException.class, () -> {
      sortService.quickSort(entity);
    });
  }

  @ParameterizedTest
  @MethodSource("invalidCases")
  void mergeSort_shouldHandleInvalidInput(NumericArrayEntity<Integer> entity) {
    assertThrows(CustomException.class, () -> {
      sortService.mergeSort(entity);
    });
  }


}
