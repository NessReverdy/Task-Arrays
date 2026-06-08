package org.nessrev.task.repo;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exception.CustomException;

import java.util.List;
import java.util.UUID;

public interface NumericArrayRepository {
  void addNumericArrayEntity(NumericArrayEntity<? extends Number> numericArrayEntity) throws CustomException;
  void updateNumericArrayEntity(NumericArrayEntity<? extends Number> numericArrayEntity) throws CustomException;
  void deleteNumericArrayEntityById(UUID id) throws CustomException;

  NumericArrayEntity<? extends Number> findById(UUID id) throws CustomException;
  List<NumericArrayEntity<? extends Number>> findAll();
}
