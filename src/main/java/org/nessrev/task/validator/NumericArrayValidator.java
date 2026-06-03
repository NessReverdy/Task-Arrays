package org.nessrev.task.validator;

import org.nessrev.task.exception.CustomException;

import java.util.List;

public interface NumericArrayValidator {
  List<String> validateNumericArray(List<String> listFromFile) throws CustomException;
}
