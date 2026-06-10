package org.nessrev.task.util.conditiion;

import java.util.function.Predicate;

public final class Condition {
  public static Predicate<Double> greaterThan(double v) {
    return x -> x > v;
  }

  public static Predicate<Double> lowerThan(double v) {
    return x -> x < v;
  }

  public static Predicate<Double> equalTo(double v) {
    double epsilon = 1e-9;
    return x -> Math.abs(x - v) < epsilon;
  }
}
