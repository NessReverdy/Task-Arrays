package org.nessrev.task.comparator.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.comparator.NumericArrayComparator;

import java.util.Comparator;

public class NumericArrayComparatorImpl implements NumericArrayComparator {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public <T extends Number> Comparator<NumericArrayEntity<T>> byId() {
    logger.debug("Creating comparator: by id");
    return Comparator.comparing(NumericArrayEntity::getId);
  }

  @Override
  public <T extends Number> Comparator<NumericArrayEntity<T>> byFirstElement() {
    logger.debug("Creating comparator: by first element");
    return Comparator.comparingDouble(e ->
      e.getNumericArray().length == 0
        ? Double.MIN_VALUE
        : e.getNumericArray()[0].doubleValue()
    );
  }

  @Override
  public <T extends Number> Comparator<NumericArrayEntity<T>> byLength() {
    logger.debug("Creating comparator: by number of elements");
    return Comparator.comparingInt(
      e -> e.getNumericArray().length
    );
  }
}
