package org.nessrev.service.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.entity.NumericArrayEntity;

import java.util.ArrayList;
import java.util.List;

public class HelperServiceImpl implements HelperService{
    private static final Logger log =
            LogManager.getLogger(HelperServiceImpl.class);
    @Override
    public void print(List<?> list) {
        log.info("List: {}", list);
    }

    @Override
    public <T extends Number> void print(NumericArrayEntity<T> numericArrayEntity) {
        List<String> list = new ArrayList<>();

        for (T element : numericArrayEntity.getNumericArray()) {
            list.add(String.valueOf(element));
        }
        print(list);
    }

    @Override
    public <T extends Number> void print(T[] array) {
        List<String> list = new ArrayList<>();

        for (T element : array) {
            list.add(String.valueOf(element));
        }
        print(list);
    }
}
