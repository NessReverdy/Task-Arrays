package org.nessrev.task.service.calculation.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;
import org.nessrev.task.service.calculation.CalculationService;
import org.nessrev.task.service.statistic.StatisticsCalculator;
import org.nessrev.task.service.statistic.impl.StatisticsCalculatorImpl;
import org.nessrev.task.util.data.CalculationData;
import org.nessrev.task.warehouse.CalculationWarehouse;
import org.nessrev.task.warehouse.impl.CalculationWarehouseImpl;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public class CalculationServiceImpl implements CalculationService {
  private static final Logger logger = LogManager.getLogger();
  private static final StatisticsCalculator calculator = new StatisticsCalculatorImpl();
  private final CalculationWarehouse warehouse =
    CalculationWarehouseImpl.getInstance();

  @Override
  public List<NumericArrayEntity<? extends Number>> searchArrayByPredicate(
    Predicate<Double> condition,
    Function<NumericArrayEntity<? extends Number>, Double> function,
    String logExplain,
    NumericArrayRepository repo
  ) {
    logger.info("Starting searchArrayByPredicate: {}", logExplain);

    List<NumericArrayEntity<? extends Number>> result =
      repo.findAll()
        .stream()
        .filter(e -> {
          Double value = function.apply(e);

          boolean ok = condition.test(value);

          logger.debug(
            "Evaluating entity: id={}, value={}, pass={}",
            e.getId(),
            value,
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
    double min = calculator.min(entity);
    double max = calculator.max(entity);
    double sum = calculator.sum(entity);
    double avg = calculator.avg(entity);

    warehouse.save(
      entity.getId(),
      new CalculationData(min, max, sum, avg)
    );
  }

  @Override
  public void removeDataFromStorage(UUID id) {
    warehouse.remove(id);
  }
}
