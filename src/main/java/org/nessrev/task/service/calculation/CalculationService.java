package org.nessrev.task.service.calculation;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CalculationService {
  <T extends Number> Optional<Double> min(NumericArrayEntity<T> arrayEntity);
  <T extends Number> Optional<Double> max(NumericArrayEntity<T> arrayEntity);
  <T extends Number> Optional<Double> sum(NumericArrayEntity<T> arrayEntity);
  <T extends Number> Optional<Double> avg(NumericArrayEntity<T> arrayEntity);

  List<NumericArrayEntity<? extends Number>> searchArrayByPredicate(
    Predicate<Double> condition,
    Function<NumericArrayEntity<? extends Number>, Optional<Double>> function,
    String logExplain,
    NumericArrayRepository repo
  );

  <T extends Number> void recalculate(NumericArrayEntity<T> entity);
  void removeDataFromStorage(UUID id);
}
