package org.nessrev.task.repo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exception.CustomException;
import org.nessrev.task.repo.NumericArrayRepository;

import java.util.*;

public class NumericArrayRepositoryImpl implements NumericArrayRepository {
  private static final Logger logger = LogManager.getLogger();
  private final Map<UUID, NumericArrayEntity<?>> entityStorage = new HashMap<>();

  @Override
  public void addNumericArrayEntity(NumericArrayEntity<?> numericArrayEntity) throws CustomException {
    UUID id = numericArrayEntity.getId();

    if (entityStorage.containsKey(id)) {
      logger.warn("Attempt to add numeric array entity with id {} already exists", id);
      throw new CustomException("Entity already exist");
    }

    entityStorage.put(id, numericArrayEntity);
    logger.info("Entity added successfully with id: {}", id);
  }

  @Override
  public void updateNumericArrayEntity(NumericArrayEntity<?> numericArrayEntity) throws CustomException {
    UUID id = numericArrayEntity.getId();
    if (!entityStorage.containsKey(id)) {
      logger.warn("Attempt to update numeric array entity with id {} does not exist", id);
      throw new CustomException("Entity doesn't exist");
    }

    entityStorage.put(id, numericArrayEntity);
    logger.info("Entity updated successfully with id: {}", id);
  }

  @Override
  public void deleteNumericArrayEntityById(UUID id) throws CustomException {
    if (!entityStorage.containsKey(id)) {
      logger.warn("Attempt to delete numeric array entity with id {} does not exist", id);
      throw new CustomException("Invalid id");
    }

    entityStorage.remove(id);
    logger.info("Entity deleted successfully with id: {}", id);
  }

  @Override
  public NumericArrayEntity<?> findById(UUID id) throws CustomException {
    NumericArrayEntity<?> entity = entityStorage.get(id);

    if (entity == null) {
      logger.error("Cannot find entity with id: {}", id);
      throw new CustomException("Invalid id");
    }

    return entity;
  }

  @Override
  public List<NumericArrayEntity<?>> findAll() {
    return new ArrayList<>(entityStorage.values());
  }
}
