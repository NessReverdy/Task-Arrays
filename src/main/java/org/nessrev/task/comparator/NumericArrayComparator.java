package org.nessrev.task.comparator;

import org.nessrev.task.entity.NumericArrayEntity;

import java.util.Comparator;

public interface NumericArrayComparator {
  Comparator<NumericArrayEntity<? extends Number>> byId();
  Comparator<NumericArrayEntity<? extends Number>> byFirstElement();
  Comparator<NumericArrayEntity<? extends Number>> byLength();
}
