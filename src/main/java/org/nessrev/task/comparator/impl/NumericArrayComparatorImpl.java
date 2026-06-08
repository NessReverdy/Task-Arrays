package org.nessrev.task.comparator.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.comparator.NumericArrayComparator;

import java.util.Comparator;

public class NumericArrayComparatorImpl implements NumericArrayComparator {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public Comparator<NumericArrayEntity<? extends Number>> byId() {
    logger.debug("Creating comparator: by id");
    return Comparator.comparing(NumericArrayEntity::getId);
  }

  @Override
  public Comparator<NumericArrayEntity<? extends Number>> byFirstElement() {
    logger.debug("Creating comparator: by first element");
    return Comparator.comparingDouble(e ->
      e.getNumericArray().length == 0
        ? Double.MIN_VALUE
        : e.getNumericArray()[0].doubleValue()
    );
  }

  @Override
  public Comparator<NumericArrayEntity<? extends Number>> byLength() {
    logger.debug("Creating comparator: by number of elements");
    return Comparator.comparingInt(
      e -> e.getNumericArray().length
    );
  }
}
