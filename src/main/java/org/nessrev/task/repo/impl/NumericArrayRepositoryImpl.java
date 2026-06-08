package org.nessrev.task.repo.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.enums.EventType;
import org.nessrev.task.exception.CustomException;
import org.nessrev.task.observer.Observer;
import org.nessrev.task.repo.Subject;
import org.nessrev.task.repo.NumericArrayRepository;

import java.util.*;

public class NumericArrayRepositoryImpl implements NumericArrayRepository, Subject {
  private static final Logger logger = LogManager.getLogger();
  private final Map<UUID, NumericArrayEntity<? extends Number>> entityStorage = new HashMap<>();
  private final List<Observer> observers = new ArrayList<>();

  private NumericArrayRepositoryImpl() {}

  private static final NumericArrayRepositoryImpl INSTANCE =
    new NumericArrayRepositoryImpl();

  public static NumericArrayRepositoryImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public void addNumericArrayEntity(
    NumericArrayEntity<? extends Number> numericArrayEntity
  ) throws CustomException {
    UUID id = numericArrayEntity.getId();

    if (entityStorage.containsKey(id)) {
      logger.warn("Attempt to add numeric array entity with id {} already exists", id);
      throw new CustomException("Entity already exist");
    }

    entityStorage.put(id, numericArrayEntity);
    logger.info("Entity added successfully with id: {}", id);
  }

  @Override
  public void updateNumericArrayEntity(
    NumericArrayEntity<? extends Number> numericArrayEntity
  ) throws CustomException {
    UUID id = numericArrayEntity.getId();

    if (!entityStorage.containsKey(id)) {
      logger.warn("Attempt to update numeric array entity with id {} does not exist", id);
      throw new CustomException("Entity doesn't exist");
    }

    entityStorage.put(id, numericArrayEntity);
    notifyObservers(numericArrayEntity, EventType.UPDATE);
    logger.info("Entity updated successfully with id: {}", id);
  }

  @Override
  public void deleteNumericArrayEntityById(UUID id) throws CustomException {
    if (!entityStorage.containsKey(id)) {
      logger.warn("Attempt to delete numeric array entity with id {} does not exist", id);
      throw new CustomException("Invalid id");
    }

    notifyObservers(findById(id), EventType.DELETE);
    entityStorage.remove(id);
    logger.info("Entity deleted successfully with id: {}", id);
  }

  @Override
  public NumericArrayEntity<? extends Number> findById(UUID id)
    throws CustomException {
    NumericArrayEntity<? extends Number> entity =
      entityStorage.get(id);

    if (entity == null) {
      logger.error("Cannot find entity with id: {}", id);
      throw new CustomException("Invalid id");
    }
    return entity;
  }

  @Override
  public List<NumericArrayEntity<? extends Number>> findAll() {
    List<NumericArrayEntity<? extends Number>> result =
      new ArrayList<>(entityStorage.values());

    logger.info("findAll() -> {} entities found", result.size());
    return result;
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void notifyObservers(NumericArrayEntity<?> entity, EventType type) {
    for (Observer observer : observers) {
      observer.update(entity, type);
    }
  }
}
