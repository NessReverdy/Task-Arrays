package org.nessrev.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.entity.NumericArrayEntity;
import org.nessrev.service.helper.HelperService;
import org.nessrev.service.helper.HelperServiceImpl;

import java.lang.reflect.Array;
import java.util.List;

public class NumericArrayFactory {
    private final HelperService helperService = new HelperServiceImpl();

    private static final Logger logger =
            LogManager.getLogger(NumericArrayFactory.class);

    public <T extends Number> NumericArrayEntity<T> createNumericArray(List<T> numericList, Class<T> clazz) {
        NumericArrayEntity<T> entity = new NumericArrayEntity<>();

        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, numericList.size());
        logger.info(
                "start creating NumericArrayEntity from list. Input list size: {}",
                numericList.size());

        for (int i = 0; i < numericList.size(); i++) {
            array[i] = numericList.get(i);
        }
        entity.setNumericArray(array);

        logger.info(
                "NumericArrayEntity successfully created. Output array size: {}",
                array.length);
        helperService.print(entity);

        return entity;
    }
}
