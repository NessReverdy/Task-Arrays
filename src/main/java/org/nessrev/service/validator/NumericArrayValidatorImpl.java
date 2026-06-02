package org.nessrev.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.service.helper.HelperService;
import org.nessrev.service.helper.HelperServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class NumericArrayValidatorImpl implements NumericArrayValidator {
    private final HelperService helperService = new HelperServiceImpl();

    private static final String FIND_NUM = "-?\\d+(\\.\\d+)?";
    private static final Logger logger =
            LogManager.getLogger(NumericArrayValidatorImpl.class);

    @Override
    public List<String> validateNumericArray(List<String> listFromFile) {
        List<String> result = new ArrayList<>();
        logger.debug("Attempting to check list");

        for (String line : listFromFile) {
            String[] tokens = line.split("[;,\\s]+");

            for (String token : tokens) {
                if (token.matches(FIND_NUM)) {
                    result.add(token);
                }
            }
        }

        logger.info("List successfully validated. Numbers count: {}", result.size());
        helperService.print(result);
        return result;
    }
}
