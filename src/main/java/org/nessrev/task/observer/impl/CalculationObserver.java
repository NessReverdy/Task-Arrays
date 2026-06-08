package org.nessrev.task.observer.impl;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.enums.EventType;
import org.nessrev.task.observer.Observer;
import org.nessrev.task.service.calculation.CalculationService;
import org.nessrev.task.service.calculation.impl.CalculationServiceImpl;

public class CalculationObserver implements Observer {
  private final CalculationService calculator = new CalculationServiceImpl();

  @Override
  public void update(NumericArrayEntity<?> entity, EventType type) {
    switch (type) {
      case UPDATE -> calculator.recalculate(entity);
      case DELETE -> calculator.removeDataFromStorage(entity.getId());
    }
  }
}
