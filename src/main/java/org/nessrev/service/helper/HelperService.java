package org.nessrev.service.helper;

import org.nessrev.entity.NumericArrayEntity;
import org.nessrev.factory.NumericArrayFactory;

import java.util.List;

public interface HelperService {
    void print(List<?> list);
    <T extends Number> void print(NumericArrayEntity<T> numericArrayEntity);
    <T extends Number> void print(T[] array);
}