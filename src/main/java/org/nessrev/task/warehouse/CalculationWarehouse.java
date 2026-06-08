package org.nessrev.task.warehouse;

import java.util.Optional;
import java.util.UUID;

public interface CalculationWarehouse {
  Optional<Double> getMin(UUID id);
  void saveMin(UUID id, Double value);
  void removeMin(UUID id);

  Optional<Double> getMax(UUID id);
  void saveMax(UUID id, Double value);
  void removeMax(UUID id);

  Optional<Double> getSum(UUID id);
  void saveSum(UUID id, Double value);
  void removeSum(UUID id);

  Optional<Double> getAvg(UUID id);
  void saveAvg(UUID id, Double value);
  void removeAvg(UUID id);
}
