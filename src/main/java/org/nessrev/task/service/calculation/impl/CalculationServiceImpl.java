package org.nessrev.task.service.calculation.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.service.calculation.CalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Optional;

public class CalculationServiceImpl implements CalculationService {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public <T extends Number> Optional<Double> min(NumericArrayEntity<T> arrayEntity) {
      T[] array = arrayEntity.getNumericArray();

      logger.debug("Calculating minimum value for array of size {}", array.length);

      double min = round(
        Arrays.stream(array)
          .mapToDouble(Number::doubleValue)
          .min()
          .orElseThrow()
      );

      logger.info("Minimum value calculated: {}", min);
      return Optional.of(min);
  }

  @Override
  public <T extends Number> Optional<Double> max(NumericArrayEntity<T> arrayEntity) {
      T[] array = arrayEntity.getNumericArray();

      logger.debug("Calculating maximum value for array of size {}", array.length);

      double max = round(
        Arrays.stream(array)
          .mapToDouble(Number::doubleValue)
          .max()
          .orElseThrow()
      );

      logger.info("Maximum value calculated: {}", max);
      return Optional.of(max);
  }

  @Override
  public <T extends Number> Optional<Double> sum(NumericArrayEntity<T> arrayEntity) {
      T[] array = arrayEntity.getNumericArray();

      logger.debug("Calculating sum value for array of size {}", array.length);

      double sum = round(
        Arrays.stream(array)
          .mapToDouble(Number::doubleValue)
          .sum()
      );

      logger.info("Sum value calculated: {}", sum);
      return Optional.of(sum);
  }

  @Override
  public <T extends Number> Optional<Double> avg(NumericArrayEntity<T> arrayEntity) {
      T[] array = arrayEntity.getNumericArray();

      logger.debug("Calculating average value for array of size {}", array.length);

      double avg = round(
        Arrays.stream(array)
          .mapToDouble(Number::doubleValue)
          .average()
          .orElseThrow()
      );

      logger.info("Average value calculated: {}", avg);
      return Optional.of(avg);
  }

  private double round(double value) {
    return BigDecimal.valueOf(value)
      .setScale(2, RoundingMode.HALF_UP)
      .doubleValue();
  }
}
