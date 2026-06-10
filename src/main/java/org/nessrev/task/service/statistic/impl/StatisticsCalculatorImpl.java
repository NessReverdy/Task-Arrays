package org.nessrev.task.service.statistic.impl;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.service.statistic.StatisticsCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class StatisticsCalculatorImpl implements StatisticsCalculator {

  @Override
  public <T extends Number> Double min(NumericArrayEntity<T> arrayEntity) {
    return round(
      Arrays.stream(arrayEntity.getNumericArray())
        .mapToDouble(Number::doubleValue)
        .min()
        .orElseThrow()
    );
  }

  @Override
  public <T extends Number> Double max(NumericArrayEntity<T> arrayEntity) {
    return round(
      Arrays.stream(arrayEntity.getNumericArray())
        .mapToDouble(Number::doubleValue)
        .max()
        .orElseThrow()
    );
  }

  @Override
  public <T extends Number> Double sum(NumericArrayEntity<T> arrayEntity) {
    return round(
      Arrays.stream(arrayEntity.getNumericArray())
        .mapToDouble(Number::doubleValue)
        .sum()
    );
  }

  @Override
  public <T extends Number> Double avg(NumericArrayEntity<T> arrayEntity) {
    return round(
      Arrays.stream(arrayEntity.getNumericArray())
        .mapToDouble(Number::doubleValue)
        .average()
        .orElseThrow()
    );
  }

  private double round(double value) {
    return BigDecimal.valueOf(value)
      .setScale(2, RoundingMode.HALF_UP)
      .doubleValue();
  }
}
