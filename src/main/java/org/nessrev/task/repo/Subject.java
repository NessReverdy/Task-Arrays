package org.nessrev.task.repo;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.enums.EventType;
import org.nessrev.task.observer.Observer;

public interface Subject {
  void addObserver(Observer observer);
  void notifyObservers(NumericArrayEntity<?> entity, EventType type);
}
