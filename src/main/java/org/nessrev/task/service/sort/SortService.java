package org.nessrev.task.service.sort;

import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exception.CustomException;

public interface SortService {
    <T extends Number> NumericArrayEntity<T> quickSort(NumericArrayEntity<T> entity) throws CustomException;
    <T extends Number> NumericArrayEntity<T> mergeSort(NumericArrayEntity<T> entity) throws CustomException;
}
