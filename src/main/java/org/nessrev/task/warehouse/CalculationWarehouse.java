package org.nessrev.task.warehouse;

import org.nessrev.task.util.data.CalculationData;

import java.util.Optional;
import java.util.UUID;

public interface CalculationWarehouse {
  Optional<CalculationData> get(UUID id);
  void save(UUID id, CalculationData data);
  void remove(UUID id);
}
