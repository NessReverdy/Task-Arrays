package org.nessrev.task.warehouse.impl;

import org.nessrev.task.warehouse.CalculationWarehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CalculationWarehouseImpl implements CalculationWarehouse {
  private final Map<UUID, Double> minValue = new HashMap<>();
  private final Map<UUID, Double> maxValue = new HashMap<>();
  private final Map<UUID, Double> sumValue = new HashMap<>();
  private final Map<UUID, Double> avgValue = new HashMap<>();

  private static final CalculationWarehouseImpl INSTANCE =
    new CalculationWarehouseImpl();

  private CalculationWarehouseImpl() {
  }

  public static CalculationWarehouseImpl getInstance() {
    return INSTANCE;
  }


  @Override
  public Optional<Double> getMin(UUID id) {
    return Optional.ofNullable(minValue.get(id));
  }

  @Override
  public void saveMin(UUID id, Double value) {
    minValue.put(id, value);
  }

  @Override
  public void removeMin(UUID id) {
    minValue.remove(id);
  }

  @Override
  public Optional<Double> getMax(UUID id) {
    return Optional.ofNullable(maxValue.get(id));
  }

  @Override
  public void saveMax(UUID id, Double value) {
    maxValue.put(id, value);
  }

  @Override
  public void removeMax(UUID id) {
    maxValue.remove(id);
  }

  @Override
  public Optional<Double> getSum(UUID id) {
    return Optional.ofNullable(sumValue.get(id));
  }

  @Override
  public void saveSum(UUID id, Double value) {
    sumValue.put(id, value);
  }

  @Override
  public void removeSum(UUID id) {
    sumValue.remove(id);
  }

  @Override
  public Optional<Double> getAvg(UUID id) {
    return Optional.ofNullable(avgValue.get(id));
  }

  @Override
  public void saveAvg(UUID id, Double value) {
    avgValue.put(id, value);
  }

  @Override
  public void removeAvg(UUID id) {
    avgValue.remove(id);
  }
}
