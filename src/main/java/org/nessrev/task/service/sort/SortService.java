package org.nessrev.task.service.sort;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;

import java.util.List;

public interface SortService {
  <T extends Number> NumericArrayEntity<T> quickSort(NumericArrayEntity<T> entity);
  <T extends Number> NumericArrayEntity<T> mergeSort(NumericArrayEntity<T> entity);
  List<NumericArrayEntity<? extends Number>> sortById(NumericArrayRepository repo);
  List<NumericArrayEntity<? extends Number>> sortByFirstElement(NumericArrayRepository repo);
  List<NumericArrayEntity<? extends Number>> sortByLength(NumericArrayRepository repo);
}
