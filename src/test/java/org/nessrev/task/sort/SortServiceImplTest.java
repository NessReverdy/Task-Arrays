package org.nessrev.task.sort;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;
import org.nessrev.task.sort.impl.SortServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SortServiceImplTest {

  private static final Integer[] SORTED_ARRAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  private static final Integer[] UNSORTED_ARRAY = {5, 8, -6, 4, 9, 8, -1, 0, 3, 7, -2};
  private static final Integer[] EMPTY_ARRAY = {};
  private static final Integer[] ONE_DIGIT = {2};
  private static final Integer[] SAME_DIGITS = {2, 2, 2, 2, 2};

  @Mock
  private NumericArrayRepository repo;

  @InjectMocks
  private SortServiceImpl sortService;

  private static Stream<Arguments> arraysForSort() {
    return Stream.of(
      Arguments.of(ONE_DIGIT, new Integer[]{2}),
      Arguments.of(SAME_DIGITS, new Integer[]{2, 2, 2, 2, 2}),
      Arguments.of(UNSORTED_ARRAY, new Integer[]{-6, -2, -1, 0, 3, 4, 5, 7, 8, 8, 9}),
      Arguments.of(SORTED_ARRAY, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}),
      Arguments.of(EMPTY_ARRAY, new Integer[]{})
    );
  }

  private static Stream<Arguments> idSortCases() {

    NumericArrayEntity<Integer> e1 =
      new NumericArrayEntity<>(UUID.fromString("00000000-0000-0000-0000-000000000003"), new Integer[]{1});

    NumericArrayEntity<Integer> e2 =
      new NumericArrayEntity<>(UUID.fromString("00000000-0000-0000-0000-000000000001"), new Integer[]{2});

    NumericArrayEntity<Integer> e3 =
      new NumericArrayEntity<>(UUID.fromString("00000000-0000-0000-0000-000000000002"), new Integer[]{3});

    return Stream.of(
      Arguments.of(List.of(e3, e1, e2), List.of(e2, e3, e1))
    );
  }

  private static Stream<Arguments> lengthSortCases() {

    NumericArrayEntity<Integer> e1 = new NumericArrayEntity<>(new Integer[]{1, 2});
    NumericArrayEntity<Integer> e2 = new NumericArrayEntity<>(new Integer[]{1});
    NumericArrayEntity<Integer> e3 = new NumericArrayEntity<>(new Integer[]{1, 2, 3});

    return Stream.of(
      Arguments.of(List.of(e1, e2, e3), List.of(e2, e1, e3))
    );
  }

  private static Stream<Arguments> firstElementSortCases() {

    NumericArrayEntity<Integer> e1 = new NumericArrayEntity<>(new Integer[]{5});
    NumericArrayEntity<Integer> e2 = new NumericArrayEntity<>(new Integer[]{1});
    NumericArrayEntity<Integer> e3 = new NumericArrayEntity<>(new Integer[]{3});

    return Stream.of(
      Arguments.of(List.of(e1, e2, e3), List.of(e2, e3, e1))
    );
  }

  @ParameterizedTest
  @MethodSource("arraysForSort")
  void quickSort_shouldSortCorrectly(Integer[] input, Integer[] expected) {

    NumericArrayEntity<Integer> entity =
      new NumericArrayEntity<>(input);

    NumericArrayEntity<Integer> actual =
      sortService.quickSort(entity);

    assertArrayEquals(expected, actual.getNumericArray());
  }

  @ParameterizedTest
  @MethodSource("arraysForSort")
  void mergeSort_shouldSortCorrectly(Integer[] input, Integer[] expected) {

    NumericArrayEntity<Integer> entity =
      new NumericArrayEntity<>(input);

    NumericArrayEntity<Integer> actual =
      sortService.mergeSort(entity);

    assertArrayEquals(expected, actual.getNumericArray());
  }

  @ParameterizedTest
  @MethodSource("idSortCases")
  void sortById_shouldSortCorrectly(
    List<NumericArrayEntity<? extends Number>> input,
    List<NumericArrayEntity<? extends Number>> expected
  ) {

    when(repo.findAll()).thenReturn(input);

    List<NumericArrayEntity<? extends Number>> result =
      sortService.sortById(repo);

    assertEquals(
      expected.stream().map(NumericArrayEntity::getId).toList(),
      result.stream().map(NumericArrayEntity::getId).toList()
    );

    verify(repo, times(1)).findAll();
  }

  @ParameterizedTest
  @MethodSource("lengthSortCases")
  void sortByLength_shouldSortCorrectly(List<NumericArrayEntity<? extends Number>> input,
                                        List<NumericArrayEntity<? extends Number>> expected) {

    when(repo.findAll()).thenReturn(input);

    List<NumericArrayEntity<? extends Number>> result =
      sortService.sortByLength(repo);

    assertEquals(
      expected.stream().map(e -> e.getNumericArray().length).toList(),
      result.stream().map(e -> e.getNumericArray().length).toList()
    );
  }

  @ParameterizedTest
  @MethodSource("firstElementSortCases")
  void sortByFirstElement_shouldSortCorrectly(List<NumericArrayEntity<? extends Number>> input,
                                              List<NumericArrayEntity<? extends Number>> expected) {

    when(repo.findAll()).thenReturn(input);

    List<NumericArrayEntity<? extends Number>> result =
      sortService.sortByFirstElement(repo);

    assertEquals(
      expected.stream().map(e -> e.getNumericArray()[0].doubleValue()).toList(),
      result.stream().map(e -> e.getNumericArray()[0].doubleValue()).toList()
    );
  }
}