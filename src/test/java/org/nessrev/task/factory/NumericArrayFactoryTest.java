package org.nessrev.task.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nessrev.task.entity.NumericArrayEntity;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumericArrayFactoryTest {

  private final NumericArrayFactory factory = new NumericArrayFactory();

  static Stream<Arguments> provideNumericData() {
    return Stream.of(
      Arguments.of(
        List.of(1, 2, 3),
        Integer.class,
        new Integer[]{1, 2, 3}
      ),
      Arguments.of(
        List.of(1.5, 2.5, 3.5),
        Double.class,
        new Double[]{1.5, 2.5, 3.5}
      ),
      Arguments.of(
        List.of(10L, 20L, 30L),
        Long.class,
        new Long[]{10L, 20L, 30L}
      )
    );
  }

  @ParameterizedTest
  @MethodSource("provideNumericData")
  @DisplayName("Should correctly create NumericArrayEntity from List")
  <T extends Number> void shouldCreateNumericArrayEntity(
    List<T> input,
    Class<T> clazz,
    T[] expected
  ) {
    NumericArrayEntity<T> result = factory.createNumericArray(input, clazz);

    assertNotNull(result);
    assertNotNull(result.getNumericArray());

    assertEquals(expected.length, result.getNumericArray().length);
    assertArrayEquals(expected, result.getNumericArray());
  }

  @Test
  @DisplayName("Should create empty array when input list is empty")
  void shouldHandleEmptyList() {
    NumericArrayEntity<Integer> result =
      factory.createNumericArray(List.of(), Integer.class);

    assertNotNull(result);
    assertNotNull(result.getNumericArray());
    assertEquals(0, result.getNumericArray().length);
  }
}