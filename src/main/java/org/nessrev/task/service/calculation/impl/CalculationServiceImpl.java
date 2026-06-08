package org.nessrev.task.service.calculation.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;
import org.nessrev.task.service.calculation.CalculationService;
import org.nessrev.task.warehouse.CalculationWarehouse;
import org.nessrev.task.warehouse.impl.CalculationWarehouseImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public class CalculationServiceImpl implements CalculationService {
  private static final Logger logger = LogManager.getLogger();
  private final CalculationWarehouse warehouse =
    CalculationWarehouseImpl.getInstance();

  @Override
  public <T extends Number> Optional<Double> min(NumericArrayEntity<T> arrayEntity) {
    T[] array = arrayEntity.getNumericArray();

    logger.debug("Calculating minimum value for array of size {}", array.length);

    Optional<Double> cachedMin =
      warehouse.getMin(arrayEntity.getId());

    if (cachedMin.isPresent()) {
      logger.info("Storage already contains minimum value for {}", arrayEntity.getId());
      return cachedMin;
    }

    double min = round(
      Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .min()
        .orElseThrow()
    );

    warehouse.saveMin(arrayEntity.getId(), min);
    logger.info("Minimum value calculated: {}", min);
    return Optional.of(min);
  }

  @Override
  public <T extends Number> Optional<Double> max(NumericArrayEntity<T> arrayEntity) {
    T[] array = arrayEntity.getNumericArray();

    logger.debug("Calculating maximum value for array of size {}", array.length);

    Optional<Double> cachedMax =
      warehouse.getMax(arrayEntity.getId());

    if (cachedMax.isPresent()) {
      logger.info("Storage already contains maximum value for {}", arrayEntity.getId());
      return cachedMax;
    }

    double max = round(
      Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .max()
        .orElseThrow()
    );

    warehouse.saveMax(arrayEntity.getId(), max);
    logger.info("Maximum value calculated: {}", max);
    return Optional.of(max);
  }

  @Override
  public <T extends Number> Optional<Double> sum(NumericArrayEntity<T> arrayEntity) {
    T[] array = arrayEntity.getNumericArray();

    logger.debug("Calculating sum value for array of size {}", array.length);

    Optional<Double> cachedSum =
      warehouse.getSum(arrayEntity.getId());

    if (cachedSum.isPresent()) {
      logger.info("Storage already contains summary value for {}", arrayEntity.getId());
      return cachedSum;
    }

    double sum = round(
      Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .sum()
    );

    warehouse.saveSum(arrayEntity.getId(), sum);
    logger.info("Sum value calculated: {}", sum);
    return Optional.of(sum);
  }

  @Override
  public <T extends Number> Optional<Double> avg(NumericArrayEntity<T> arrayEntity) {
    T[] array = arrayEntity.getNumericArray();

    logger.debug("Calculating average value for array of size {}", array.length);

    Optional<Double> cachedAvg =
      warehouse.getAvg(arrayEntity.getId());

    if (cachedAvg.isPresent()) {
      logger.info("Storage already contains average value for {}", arrayEntity.getId());
      return cachedAvg;
    }

    double avg = round(
      Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .average()
        .orElseThrow()
    );

    warehouse.saveAvg(arrayEntity.getId(), avg);
    logger.info("Average value calculated: {}", avg);
    return Optional.of(avg);
  }

  @Override
  public List<NumericArrayEntity<? extends Number>> searchArrayByPredicate(
    Predicate<Double> condition,
    Function<NumericArrayEntity<? extends Number>, Optional<Double>> function,
    String logExplain,
    NumericArrayRepository repo
  ) {
    logger.info("Starting searchArrayByPredicate: {}", logExplain);

    List<NumericArrayEntity<? extends Number>> result =
      repo.findAll()
        .stream()
        .filter(e -> {
          Optional<Double> valueOpt = function.apply(e);

          boolean ok = valueOpt
            .map(condition::test)
            .orElse(false);

          logger.debug(
            "Evaluating entity: id={}, value={}, pass={}",
            e.getId(),
            valueOpt.orElse(null),
            ok
          );

          return ok;
        })
        .toList();

    logger.info(
      "searchArrayByPredicate finished: found {} entities matching condition '{}'",
      result.size(),
      logExplain
    );
    return result;
  }

  @Override
  public <T extends Number> void recalculate(NumericArrayEntity<T> entity) {
    Optional<Double> min = min(entity);
    min.ifPresent(v -> warehouse.saveMin(entity.getId(), v));

    Optional<Double> max = max(entity);
    max.ifPresent(v -> warehouse.saveMax(entity.getId(), v));

    Optional<Double> sum = sum(entity);
    sum.ifPresent(v -> warehouse.saveSum(entity.getId(), v));

    Optional<Double> avg = avg(entity);
    avg.ifPresent(v -> warehouse.saveAvg(entity.getId(), v));
  }

  @Override
  public void removeDataFromStorage(UUID id) {
    warehouse.removeMin(id);
    warehouse.removeMax(id);
    warehouse.removeSum(id);
    warehouse.removeAvg(id);
  }

  private double round(double value) {
    return BigDecimal.valueOf(value)
      .setScale(2, RoundingMode.HALF_UP)
      .doubleValue();
  }
}
