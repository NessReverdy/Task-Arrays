package org.nessrev.task.warehouse.impl;

import org.nessrev.task.util.data.CalculationData;
import org.nessrev.task.warehouse.CalculationWarehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CalculationWarehouseImpl implements CalculationWarehouse {
  private final Map<UUID, CalculationData> warehouse = new HashMap<>();

  private static final CalculationWarehouseImpl INSTANCE =
    new CalculationWarehouseImpl();

  private CalculationWarehouseImpl() {
  }

  public static CalculationWarehouseImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public Optional<CalculationData> get(UUID id) {
    return Optional.ofNullable(warehouse.get(id));
  }

  @Override
  public void save(UUID id, CalculationData data) {
    warehouse.put(id, data);
  }

  @Override
  public void remove(UUID id) {
    warehouse.remove(id);
  }
}
