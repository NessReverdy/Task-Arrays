package org.nessrev.task.entity;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class NumericArrayEntity<T extends Number> {
  private final UUID idArray;
  private T[] numericArray;

  public NumericArrayEntity() {
    idArray = UUID.randomUUID();
  }

  public NumericArrayEntity(T[] numericArray) {
    idArray = UUID.randomUUID();
    setNumericArray(numericArray);
  }

  public NumericArrayEntity(UUID idArray, T[] array) {
    this.idArray = idArray;
    this.numericArray = array;
  }

  public T[] getNumericArray() {
    return numericArray == null
      ? null
      : Arrays.copyOf(numericArray, numericArray.length);
  }

  public void setNumericArray(T[] newArray) {
    numericArray = newArray == null
      ? null
      : Arrays.copyOf(newArray, newArray.length);
  }

  public UUID getId() {
    return idArray;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NumericArrayEntity<?> that = (NumericArrayEntity<?>) o;
    return Objects.equals(idArray, that.idArray) && Objects.deepEquals(numericArray, that.numericArray);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idArray, Arrays.hashCode(numericArray));
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("NumericArrayEntity{");

    stringBuilder.append("idArray=").append(idArray);
    stringBuilder.append(", numericArray=[");

    if (numericArray != null) {
      for (int i = 0; i < numericArray.length; i++) {
        stringBuilder.append(numericArray[i]);

        if (i < numericArray.length - 1) {
          stringBuilder.append(", ");
        }
      }
    }

    stringBuilder.append("]}");
    return stringBuilder.toString();
  }
}
