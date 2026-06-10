package org.nessrev.task.warehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nessrev.task.util.data.CalculationData;
import org.nessrev.task.warehouse.impl.CalculationWarehouseImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CalculationWarehouseImplTest {

  private final CalculationWarehouseImpl warehouse =
    CalculationWarehouseImpl.getInstance();

  private UUID id;
  private CalculationData data;

  @BeforeEach
  void setUp() {
    id = UUID.randomUUID();
    data = new CalculationData(1.0, 10.0, 55.0, 5.5);

    // чистим состояние между тестами
    warehouse.remove(id);
  }

  @Test
  void shouldSaveAndGetData() {
    warehouse.save(id, data);

    var result = warehouse.get(id);

    assertTrue(result.isPresent());
    assertEquals(data, result.get());
  }

  @Test
  void shouldReturnEmptyWhenDataNotExists() {
    var result = warehouse.get(UUID.randomUUID());

    assertTrue(result.isEmpty());
  }

  @Test
  void shouldRemoveData() {
    warehouse.save(id, data);

    warehouse.remove(id);

    assertTrue(warehouse.get(id).isEmpty());
  }

  @Test
  void shouldOverrideExistingData() {
    var newData = new CalculationData(2.0, 20.0, 100.0, 10.0);

    warehouse.save(id, data);
    warehouse.save(id, newData);

    assertEquals(newData, warehouse.get(id).get());
  }
}