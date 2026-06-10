package org.nessrev.task.service.calculation;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CalculationService {
  List<NumericArrayEntity<? extends Number>> searchArrayByPredicate(
    Predicate<Double> condition,
    Function<NumericArrayEntity<? extends Number>, Double> function,
    String logExplain,
    NumericArrayRepository repo
  );

  <T extends Number> void recalculate(NumericArrayEntity<T> entity);
  void removeDataFromStorage(UUID id);
}
