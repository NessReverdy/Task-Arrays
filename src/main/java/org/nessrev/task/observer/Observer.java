package org.nessrev.task.observer;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.enums.EventType;

public interface Observer {
  void update(NumericArrayEntity<?> entity, EventType type);
}
