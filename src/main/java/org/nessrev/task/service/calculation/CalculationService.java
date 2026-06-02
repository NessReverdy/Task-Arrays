package org.nessrev.task.service.calculation;

import org.nessrev.task.entity.NumericArrayEntity;

import java.util.Optional;

public interface CalculationService {
    <T extends Number> Optional<Double> min(NumericArrayEntity<T> arrayEntity);
    <T extends Number> Optional<Double> max(NumericArrayEntity<T> arrayEntity);
    <T extends Number> Optional<Double> sum(NumericArrayEntity<T> arrayEntity);
    <T extends Number> Optional<Double> avg(NumericArrayEntity<T> arrayEntity);
}
