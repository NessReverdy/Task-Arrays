package org.nessrev.task.service.sort.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.comparator.NumericArrayComparator;
import org.nessrev.task.comparator.impl.NumericArrayComparatorImpl;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.repo.NumericArrayRepository;
import org.nessrev.task.repo.impl.NumericArrayRepositoryImpl;
import org.nessrev.task.service.sort.SortService;

import java.util.List;
import java.util.stream.Collectors;

public class SortServiceImpl implements SortService {
  private final Logger logger = LogManager.getLogger();
  private final NumericArrayComparator comparator = new NumericArrayComparatorImpl();

  @Override
  public <T extends Number>  NumericArrayEntity<T> quickSort(NumericArrayEntity<T> entity){
      logger.info("QuickSort started");
      T[] arr = entity.getNumericArray();

      quickSort(arr, 0, arr.length - 1);
      entity.setNumericArray(arr);
      logger.info("QuickSort finished");

      return entity;
  }

  @Override
  public <T extends Number>  NumericArrayEntity<T> mergeSort(NumericArrayEntity<T> entity){
      logger.info("MergeSort started");
      T[] arr = entity.getNumericArray();

      mergeSort(arr, 0, arr.length - 1);
      entity.setNumericArray(arr);
      logger.info("MergeSort finished");

      return entity;
  }

  @Override
  public List<NumericArrayEntity<? extends Number>> sortById(NumericArrayRepository repo) {
    List<NumericArrayEntity<? extends Number>> entities = repo.findAll();
    logger.info("Before sorting by id:\n {}", format(entities));

    entities.sort(comparator.byId());

    logger.info("After sorting by id:\n {}", format(entities));
    return entities;
  }

  @Override
  public List<NumericArrayEntity<? extends Number>> sortByFirstElement(NumericArrayRepository repo) {

    List<NumericArrayEntity<? extends Number>> entities = repo.findAll();
    logger.info("Before sorting by first element:\n {}", format(entities));

    entities.sort(comparator.byFirstElement());

    logger.info("After sorting by first element:\n {}", format(entities));
    return entities;
  }

  @Override
  public List<NumericArrayEntity<? extends Number>> sortByLength(NumericArrayRepository repo) {
    List<NumericArrayEntity<? extends Number>> entities = repo.findAll();
    logger.info("Before sorting by length:\n {}", format(entities));

    entities.sort(comparator.byLength());

    logger.info("After sorting by length:\n {}", format(entities));
    return entities;
  }

  private <T extends Number> void mergeSort(T[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2;

    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right);
  }

  @SuppressWarnings("unchecked")
  private <T extends Number> void merge(T[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    T[] L = (T[]) new Number[n1];
    T[] R = (T[]) new Number[n2];

    for (int i = 0; i < n1; i++)
      L[i] = arr[left + i];

    for (int j = 0; j < n2; j++)
      R[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = left;

    while (i < n1 && j < n2) {
      if (L[i].doubleValue() <= R[j].doubleValue()) {
        arr[k++] = L[i++];
      } else {
        arr[k++] = R[j++];
      }
    }

    while (i < n1) {
      arr[k++] = L[i++];
    }

    while (j < n2) {
      arr[k++] = R[j++];
    }
  }

  private <T extends Number> void quickSort(T[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    int pivotIndex = partition(arr, low, high);

    quickSort(arr, low, pivotIndex - 1);
    quickSort(arr, pivotIndex + 1, high);
  }

  private <T extends Number> int partition(T[] arr, int low, int high) {
    double pivot = arr[high].doubleValue();
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (arr[j].doubleValue() <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }

    swap(arr, i + 1, high);
    return i + 1;
  }

  private <T extends Number> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private <T> String format(List<T> list) {
    return list.stream()
      .map(Object::toString)
      .collect(Collectors.joining("\n"));
  }
}
