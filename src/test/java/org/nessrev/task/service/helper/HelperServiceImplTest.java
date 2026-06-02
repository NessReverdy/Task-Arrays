package org.nessrev.task.service.helper;

import org.junit.jupiter.api.Test;
import org.nessrev.task.entity.NumericArrayEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HelperServiceImplTest {
    private static final HelperServiceImpl helperService =
            new HelperServiceImpl();

    @Test
    void printList_shouldNotThrowException() {
        List<String> list = List.of("1", "2", "3");

        assertDoesNotThrow(() -> helperService.print(list));
    };

    @Test
    void printArray_shouldNotThrowException() {
        Integer[] array = {1, 2, 3};

        assertDoesNotThrow(() -> helperService.print(array));
    }

    @Test
    void printEntity_shouldNotThrowException() {
        NumericArrayEntity<Integer> entity =
                new NumericArrayEntity<>(new Integer[]{1, 2, 3});

        assertDoesNotThrow(() -> helperService.print(entity));
    }

    @Test
    void printArray_shouldConvertCorrectly() {
        Integer[] array = {1, 2, 3};

        assertDoesNotThrow(() -> helperService.print(array));
    }

    @Test
    void printEntity_shouldConvertCorrectly() {
        NumericArrayEntity<Integer> entity =
                new NumericArrayEntity<>(new Integer[]{1, 2, 3});

        assertDoesNotThrow(() -> helperService.print(entity));
    }
}
