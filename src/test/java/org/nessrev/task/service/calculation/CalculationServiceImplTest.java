package org.nessrev.task.service.calculation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;
import org.nessrev.task.service.calculation.impl.CalculationServiceImpl;
import org.nessrev.task.util.data.CalculationData;
import org.nessrev.task.warehouse.CalculationWarehouse;
import org.nessrev.task.warehouse.impl.CalculationWarehouseImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceImplTest {

  private static final Integer[] INTEGER_ARRAY = {1, 2, 3};
  private static final Integer[] NEGATIVE_ARRAY = {-5, -7, -3};
  private static final Integer[] ONE_DIGIT = {2};

  private static final NumericArrayEntity<Integer> INTEGER_ENTITY =
    new NumericArrayEntity<>(INTEGER_ARRAY);

  private static final NumericArrayEntity<Integer> NEGATIVE_ENTITY =
    new NumericArrayEntity<>(NEGATIVE_ARRAY);

  private static final NumericArrayEntity<Integer> ENTITY_WITH_ONE_DIGIT =
    new NumericArrayEntity<>(ONE_DIGIT);


  @Mock
  private NumericArrayRepository repo;

  @InjectMocks
  private CalculationServiceImpl calculationService;

  private final CalculationWarehouse warehouse =
    CalculationWarehouseImpl.getInstance();

  private static Stream<Arguments> recalculateCases() {
    return Stream.of(
      Arguments.of(
        INTEGER_ENTITY,
        new CalculationData(1.0, 3.0, 6.0, 2.0)
      ),
      Arguments.of(
        NEGATIVE_ENTITY,
        new CalculationData(-7.0, -3.0, -15.0, -5.0)
      ),
      Arguments.of(
        ENTITY_WITH_ONE_DIGIT,
        new CalculationData(2.0, 2.0, 2.0, 2.0)
      )
    );
  }

  @ParameterizedTest
  @MethodSource("recalculateCases")
  void recalculate_shouldSaveCalculationData(
    NumericArrayEntity<Integer> entity,
    CalculationData expected
  ) {
    calculationService.recalculate(entity);

    Optional<CalculationData> actual =
      warehouse.get(entity.getId());

    assertEquals(Optional.of(expected), actual);
  }

  @Test
  void removeDataFromStorage_shouldRemoveData() {
    calculationService.recalculate(INTEGER_ENTITY);

    calculationService.removeDataFromStorage(
      INTEGER_ENTITY.getId()
    );

    assertTrue(
      warehouse.get(INTEGER_ENTITY.getId()).isEmpty()
    );
  }

  @Test
  void searchArrayByPredicate_shouldFilterCorrectly() {

    NumericArrayEntity<Integer> e1 = new NumericArrayEntity<>(new Integer[]{1, 2, 3});
    NumericArrayEntity<Integer> e2 = new NumericArrayEntity<>(new Integer[]{10, 20, 30});

    when(repo.findAll()).thenReturn(List.of(e1, e2));

    Function<NumericArrayEntity<? extends Number>, Double> function =
      e -> e.getNumericArray()[0].doubleValue();

    Predicate<Double> condition = value -> value > 5;

    List<NumericArrayEntity<? extends Number>> result =
      calculationService.searchArrayByPredicate(
        condition,
        function,
        "test",
        repo
      );

    assertAll(
      () -> assertEquals(1, result.size()),
      () -> assertEquals(e2.getId(), result.get(0).getId())
    );

    verify(repo, times(1)).findAll();
  }
}