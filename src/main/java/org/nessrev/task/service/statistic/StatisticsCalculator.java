package org.nessrev.task.service.statistic;

import org.nessrev.task.entity.NumericArrayEntity;

public interface StatisticsCalculator {
  <T extends Number> Double min(NumericArrayEntity<T> arrayEntity);
  <T extends Number> Double max(NumericArrayEntity<T> arrayEntity);
  <T extends Number> Double sum(NumericArrayEntity<T> arrayEntity);
  <T extends Number> Double avg(NumericArrayEntity<T> arrayEntity);
}
