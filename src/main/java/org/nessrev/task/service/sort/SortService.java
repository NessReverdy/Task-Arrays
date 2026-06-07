package org.nessrev.task.service.sort;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;

import java.util.List;

public interface SortService {
  <T extends Number> NumericArrayEntity<T> quickSort(NumericArrayEntity<T> entity);
  <T extends Number> NumericArrayEntity<T> mergeSort(NumericArrayEntity<T> entity);
  <T extends Number> List<NumericArrayEntity<T>> sortById(NumericArrayRepository<T> repo);
  <T extends Number> List<NumericArrayEntity<T>> sortByFirstElement(NumericArrayRepository<T> repo);
  <T extends Number> List<NumericArrayEntity<T>> sortByLength(NumericArrayRepository<T> repo);
}
