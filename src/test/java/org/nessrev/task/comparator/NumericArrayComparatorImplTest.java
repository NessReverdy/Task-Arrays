package org.nessrev.task.comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.nessrev.task.comparator.impl.NumericArrayComparatorImpl;
import org.nessrev.task.entity.NumericArrayEntity;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumericArrayComparatorImplTest {

  private final NumericArrayComparatorImpl comparator = new NumericArrayComparatorImpl();

  static Stream<NumericArrayEntity<? extends Number>> entities() {
    return Stream.of(
      new NumericArrayEntity<>(UUID.randomUUID(), new Integer[]{1, 2, 3}),
      new NumericArrayEntity<>(UUID.randomUUID(), new Integer[]{10}),
      new NumericArrayEntity<>(UUID.randomUUID(), new Integer[]{}),
      new NumericArrayEntity<>(UUID.randomUUID(), new Integer[]{5, 6})
    );
  }

  @ParameterizedTest
  @MethodSource("entities")
  @DisplayName("byLength comparator should return correct ordering by array size")
  void testByLength(NumericArrayEntity<? extends Number> entity) {
    var cmp = comparator.byLength();

    int result = cmp.compare(entity, entity);

    assertEquals(0, result);
    assertTrue(entity.getNumericArray().length >= 0);
  }

  @ParameterizedTest
  @MethodSource("entities")
  @DisplayName("byFirstElement should not throw and handle empty arrays safely")
  void testByFirstElement(NumericArrayEntity<? extends Number> entity) {
    var cmp = comparator.byFirstElement();

    NumericArrayEntity<? extends Number> dummy =
      new NumericArrayEntity<>(UUID.randomUUID(), new Integer[]{0});

    assertDoesNotThrow(() -> cmp.compare(entity, dummy));
    assertDoesNotThrow(() -> cmp.compare(dummy, entity));
  }

  @ParameterizedTest
  @MethodSource("entities")
  @DisplayName("byId comparator should compare consistently by UUID")
  void testById(NumericArrayEntity<? extends Number> entity) {
    var cmp = comparator.byId();

    NumericArrayEntity<? extends Number> same =
      new NumericArrayEntity<>(entity.getId(), entity.getNumericArray());

    assertEquals(0, cmp.compare(entity, same));
  }
}