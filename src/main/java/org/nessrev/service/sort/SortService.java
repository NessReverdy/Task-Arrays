package org.nessrev.service.sort;

import org.nessrev.entity.NumericArrayEntity;

public interface SortService {
    <T extends Number> NumericArrayEntity<T> quickSort(NumericArrayEntity<T> entity);
    <T extends Number> NumericArrayEntity<T> mergeSort(NumericArrayEntity<T> entity);
}
