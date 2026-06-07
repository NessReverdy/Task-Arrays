package org.nessrev.task.comparator;

import org.nessrev.task.entity.NumericArrayEntity;

import java.util.Comparator;

public interface NumericArrayComparator {
  <T extends Number> Comparator<NumericArrayEntity<T>> byId();
  <T extends Number> Comparator<NumericArrayEntity<T>> byFirstElement();
  <T extends Number> Comparator<NumericArrayEntity<T>> byLength();
}
