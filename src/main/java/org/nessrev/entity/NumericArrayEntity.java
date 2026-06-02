package org.nessrev.entity;

import java.util.Arrays;

public class NumericArrayEntity<T extends Number> {
    private T[] numericArray;

    public T[] getNumericArray() {
        return numericArray == null ? null : Arrays.copyOf(numericArray, numericArray.length);
    }

    public void setNumericArray(T[] newArray) {
        numericArray = Arrays.copyOf(newArray, newArray.length);
    }

    public NumericArrayEntity() {}

    public NumericArrayEntity(T[] numericArray) {
        this.numericArray = numericArray;
    }
}
