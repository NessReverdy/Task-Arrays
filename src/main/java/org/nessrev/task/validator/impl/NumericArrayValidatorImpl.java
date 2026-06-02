package org.nessrev.task.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NumericArrayValidatorImpl implements NumericArrayValidator {

    private static final String DECIMAL_NUMBER_PATTERN = "-?\\d+(\\.\\d+)?";
    private static final Logger logger =
            LogManager.getLogger(NumericArrayValidatorImpl.class);

    @Override
    public List<String> validateNumericArray(List<String> listFromFile) {
        List<String> result = new ArrayList<>();
        logger.debug("Attempting to check list");

        for (String line : listFromFile) {
            String[] tokens = line.split("[;,\\s]+");

            for (String token : tokens) {
                if (token.matches(DECIMAL_NUMBER_PATTERN)) {
                    result.add(token);
                }
            }
        }

        logger.info("List successfully validated. Numbers count: {}", result.size());
        return result;
    }
}
