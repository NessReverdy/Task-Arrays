package org.nessrev.task.repo;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exception.CustomException;

import java.util.List;
import java.util.UUID;

public interface NumericArrayRepository {
  void addNumericArrayEntity(NumericArrayEntity<?> numericArrayEntity) throws CustomException;
  void updateNumericArrayEntity(NumericArrayEntity<?> numericArrayEntity) throws CustomException;
  void deleteNumericArrayEntityById(UUID id) throws CustomException;

  NumericArrayEntity<?> findById(UUID id) throws CustomException;
  List<NumericArrayEntity<?>> findAll();
}
