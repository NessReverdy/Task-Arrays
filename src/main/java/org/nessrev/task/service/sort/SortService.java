package org.nessrev.service.sort;

import org.nessrev.entity.NumericArrayEntity;
import org.nessrev.exceptions.CustomException;

public interface SortService {
    <T extends Number> NumericArrayEntity<T> quickSort(NumericArrayEntity<T> entity) throws CustomException;
    <T extends Number> NumericArrayEntity<T> mergeSort(NumericArrayEntity<T> entity) throws CustomException;
}
