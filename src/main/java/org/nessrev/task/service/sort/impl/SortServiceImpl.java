package org.nessrev.task.service.sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.exceptions.CustomException;

public class SortServiceImpl implements SortService {
    private final Logger logger =
            LogManager.getLogger(SortServiceImpl.class);

    @Override
    public <T extends Number> NumericArrayEntity<T> quickSort(NumericArrayEntity<T> entity) throws CustomException {
        if (isEntityValid(entity)) {
            logger.info("QuickSort started");
            T[] arr = entity.getNumericArray();

            quickSort(arr, 0, arr.length - 1);
            entity.setNumericArray(arr);
            logger.info("QuickSort finished");

            return entity;
        } else {
            throw new CustomException("The entity is null");
        }
    }

    @Override
    public <T extends Number> NumericArrayEntity<T> mergeSort(NumericArrayEntity<T> entity) throws CustomException {
        if (isEntityValid(entity)) {
            logger.info("MergeSort started");
            T[] arr = entity.getNumericArray();

            mergeSort(arr, 0, arr.length - 1);
            entity.setNumericArray(arr);
            logger.info("MergeSort finished");

            return entity;
        } else {
            throw new CustomException("The entity is null");
        }
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

    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private   <T extends Number> boolean isEntityValid(NumericArrayEntity<T> entity) {
        if (entity == null) {
            logger.debug("Sorting skipped: entity is null");
            return false;
        }

        T[] array = entity.getNumericArray();
        if (array == null || array.length == 0) {
            logger.debug("Sorting skipped: array is empty");
            return false;
        }

        return true;
    }
}
