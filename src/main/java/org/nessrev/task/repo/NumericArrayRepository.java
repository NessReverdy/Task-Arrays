package org.nessrev.task.repo;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exception.CustomException;

import java.util.List;
import java.util.UUID;

public interface NumericArrayRepository<T extends Number> {
  void addNumericArrayEntity(NumericArrayEntity<T> numericArrayEntity) throws CustomException;
  void updateNumericArrayEntity(NumericArrayEntity<T> numericArrayEntity) throws CustomException;
  void deleteNumericArrayEntityById(UUID id) throws CustomException;

  NumericArrayEntity<T> findById(UUID id) throws CustomException;
  List<NumericArrayEntity<T>> findAll();
}
